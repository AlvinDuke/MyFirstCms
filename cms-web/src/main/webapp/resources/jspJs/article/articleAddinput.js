var setting = {
		view : {
			showIcon : showIconForTree
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick: zTreeBeforeClick
		}
	};

function showIconForTree(treeId, treeNode) {
	return !treeNode.isParent;
}

function zTreeBeforeClick(treeId, treeNode) {
	//选择的栏目不是导航栏，才可以向栏目内添加内容
	if(treeNode.isNavCate != 1){
		$("#categoryId").val(treeNode.id);
		$("#categoryTxt").val(treeNode.name);
	}
	else{
		alert("导航栏不可以添加具体内容");
		return false;
	}
};

$(function() {
	$.fn.zTree.init($("#tree"), setting, zNodes);
	//展示组内的栏目，全部展开
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	treeObj.expandAll(true);
	
	//日期选择
	//文章id值存在说明是更新功能，创建日期日期不可更新
	if($("#artId").val()){
		$( "#datepicker" ).datepicker( "isDisabled" );
	}else{
		$( "#datepicker" ).datepicker();
	}
	
	//栏目隐藏/显示
	$("#categoryTxt").on("focus",function(){
		$(".cateholder").fadeIn("fast");
	}).on("blur",function(){
		$(".cateholder").fadeOut("fast");
	});
	
	/*
     * 更新功能所需JS
     */
    //当前画面如果是更新功能使用，那么自动将当前文章已有的关键字添加出来
	var keywords = $("#keywords").val();
	var keys = keywords.split("|");
	var actualCount = 0;//关键字的有效长度（去除无效内容，"",null等）
	
	//逐个填充到容器中显示
	for (var i = 0; i < keys.length; i++) {
		if(keys[i] == "" || keys[i] == null) continue;
		addKeywordToHolder(keys[i]);
		actualCount++;
	}
	//更新时存在关键字才显示容器
	if(actualCount>0){
		$("#keywordDiv").slideDown(100);
	}
	
	//当前文章所含的附件个数
	var size = $("#attachsSize").val();
	//存在附件则显示附件列表
	if(size!=0){
		$("#attachDiv").css("display","block");
	}
	
	/*
	 * 更新，新增共用JS
	 */
	//关键字添加
	var keywordCountsleft = (5 - actualCount);
	var keywordStrs = $("#keywords").val();
	$("#artKeywords").on("keydown",function(event){
		//回车键按下，将文本框里的值作为一个关键字
		if(event.keyCode == '188'){
			//数量检查
			if(keywordCountsleft < 1){
				alert("最多添加五个关键字");
				return;
			}
			keywordCountsleft--;
			//将关键字填充到div中
			var key = $(this).val();
			keywordStrs +=key+"|";
			$("#keywordDiv").slideDown(100);
			
			addKeywordToHolder(key);
			
			//拼接已添加关键字，准备传递
			$("#keywords").val(keywordStrs);
			//清空输入框，变更提示
			$("#artKeywords").val("");
			var msg = "";
			if(keywordCountsleft == 0){
				msg = "关键字已满";
			}else{
				msg = "还可以添加"+keywordCountsleft+"个关键字";
			}
			$("#artKeywords").attr("placeHolder",msg);
		}
		
	});
	
	/**
	 * 添加关键字到容器
	 */
	function addKeywordToHolder(key){
		var divObj = "<div class='kwDiv'><span class='kwhoder'>"+key+"</span><a class='kwhoderEraser' title='删除此关键字'>X</a></div>";
		$("#keywordDiv").append(divObj);
	}
	
	//关键字删除
	$("#keywordDiv").on("click",".kwhoderEraser",function(){
		$(this).parent().remove();
		keywordCountsleft++;
		//检查关键字容器中是否含有关键字，如果不含，隐藏关键字容器
		var kwDivs = $("#keywordDiv").find("div.kwDiv");
		if(kwDivs.length==0){
			$("#keywordDiv").slideUp(100);
		}
		$("#artKeywords").attr("placeHolder","还可以添加"+keywordCountsleft+"个关键字");
		
		//关键字再次拼接
		keywordStrs = "";
		$("#keywordDiv").find("span").each(function(){
			keywordStrs +=$(this).text()+"|";
		});
		$("#keywords").val(keywordStrs);
	});
	
	//编辑器添加，关闭过滤模式，保留所有标签
	KindEditor.options.filterMode = false;
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id',{
        	cssPath: root + '/resources/js/plugins/code/prettify.css',//插入，编辑时使用到的样式，主要放置了插入代码的时候的样式
            width : '100%',
            height: '300px',
            resizeType: 1,
            uploadJson : root + '/article/uploadify'
        });
        prettyPrint();  
    });
    
    //uploadify
	setTimeout(function(){//setTimeout防止chrome浏览器因为缓存的问题导致页面崩溃
		$('#file_upload').uploadify({
			'buttonText' : '选择文件',
			'width':'94',
			'fileObjName' : 'attach',
			'fileTypeExts' : '*.gif;*.jpg;*.png;*.txt;*.doc;*.docx;*.xls;*.xlsx;*.rar;*.zip;*.pdf',
			'swf'      : root + '/resources/js/uploadify.swf',
			'uploader' : root + '/article/uploadify',
			'auto':false,
	        'onUploadSuccess' : function(file, data, response) {
	            var obj = $.parseJSON(data);
	            //拼接附件id,准备传递
	            var ids = $("#attachIds").val();
	            $("#attachIds").val(ids + "," + obj.id);
	            //显示已上传的附件
	            $("#attachDiv").css("display","block");
	            $("#attachList").append("<tr><td>"+obj.oriName
	            					  +"</td><td>"+(obj.size)/1000+"KB</td><td>"
	            					  			  +obj.extention+"</td><td>"
	            					  			  +obj.accessUrl+"</td><td><a class='btn btn-xs btn-danger deleteAtt' id='"
	            					  			  +obj.id+"'>删除</a></td></tr>");
	        }
		});
	},10);
    
    //异步删除附件
    $("#attachList").on("click",".deleteAtt",function(event){
    	var $curtr = $(this).parent().parent();
    	attachDelete.deleteAttachment(this.id,function(data){
    		//删除所在行
    		$curtr.remove();
    		//重新拼接附件的id
    		$("#attachIds").val("");
    		var ids = "";
    		$("#attachList").find("a").each(function(){
    			ids += this.id + ",";
    		});
    		$("#attachIds").val(ids);
    		//查看是否为空表格，空表格则直接隐藏容器
    		var $trs = $("#attachList").find("tr");
    		if($trs.length == 0){
    			$("#attachDiv").css("display","none");
    		}
    	});
    });
    
});

