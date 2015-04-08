//uploadify
$(function() {
	
	$("#addIdxImg").click(function(){
		//图片必须上传
		if($("#idxId").val() == ""){
			alert("必须上传图片");
			return false;
		}
	});	
	
	// Create variables (in this scope) to hold the API and image size
    var jcrop_api,
        boundx,
        boundy,
        // Grab some information about the preview pane
        $preview = $('#preview-pane'),
        $pcnt = $('#preview-pane .preview-container'),
        $pimg = $('#preview-pane .preview-container img'),
        xsize = $pcnt.width(),
        ysize = $pcnt.height(),
        cropedY;
	
	setTimeout(function(){//setTimeout防止chrome浏览器因为缓存的问题导致页面崩溃
		$('#file_upload').uploadify({
			'buttonText' : '选择文件',
			'width':'94',
			'multi'    : false,
			'fileObjName' : 'attach',
			'fileTypeExts' : '*.gif;*.jpg;*.png;',
			'swf'      : root + '/resources/js/uploadify.swf',
			'uploader' : root + '/system/uploadify',
			'auto':false,
	        'onUploadSuccess' : function(file, data, response) {
	        	var obj = $.parseJSON(data);
	        	if(obj.error){
	        		//有异常则终止
	        		alert(obj.msg);
	        		return;
	        	}
	        	//清空原始内容
	        	$(".target").empty().append('<span class="cropTip">选择合适的部分后，保存信息</span><img id="target" class="idximg">');
	        	//图像预览可见
	        	$(".imgPreview").css("display","block");
	            //保存上传信息的id
	        	$("#idxId").val(obj.id);
	            //设置图片访问资源，设置宽度，高度
	            $(".idximg").attr("src",obj.accessUrl+obj.newName);
	            $('#target').width(obj.width/2).height(obj.height/2);
	            //jcrop初始化
	            $('#target').Jcrop({
	              onChange: updatePreview,
	              onSelect: updatePreview,
	              setSelect:[0,0,obj.width/2,250 ],//初始化选取区域
	              allowResize:false,
	              allowSelect:false,
	              bgOpacity:   .3
	            },function(){
	              // Use the API to get the real image size
	              var bounds = this.getBounds();
	              boundx = bounds[0];
	              boundy = bounds[1];
	              // Store the API in the jcrop_api variable
	              jcrop_api = this;

	              // Move the preview into the jcrop container for css positioning
	              $preview.appendTo(jcrop_api.ui.holder);
	            });

	            function updatePreview(c)
	            {
	              if (parseInt(c.w) > 0)
	              {
	                var rx = xsize / c.w;
	                var ry = ysize / c.h;
	                
	                //每次更新时保存当前的纵轴坐标
	                $("#cropedY").val((c.y)*2);
	                $pimg.css({
	                  width: Math.round(rx * boundx) + 'px',
	                  height: Math.round(ry * boundy) + 'px',
	                  marginLeft: '-' + Math.round(rx * c.x) + 'px',
	                  marginTop: '-' + Math.round(ry * c.y) + 'px'
	                });
	              }
	            };
	        }
		});
	},5);
});
