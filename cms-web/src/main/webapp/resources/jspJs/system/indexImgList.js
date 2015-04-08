$(function(){
	//更新点击之后，操作框出现
	$("#updateIndex").on("click",function(){
		//可操作项显示
		$("td:nth-child(1)").find("input").css("visibility","visible");
		$("#cofim").fadeIn("fast");
		$("#cacle").fadeIn("fast");
		//提示显示
		$(".updTip").fadeIn("fast");
		setTimeout(function(){
			$(".updTip").fadeOut("fast");
		},5000);
	});
	
	//顺序多选框点击
	var index = 0;
	var orderedIds = [];
	$(".forOpt").on("click",function(){
		//选中
		if(this.checked){
			index++
			//在多选框下添加序号
			$(this).next().css("font-size","35px").html(index);
			orderedIds.push(this.value);
		}
		else{
			//为方便起见，不允许取消
			return false;
		}
	});
	
	//保存图片顺序
	$("#cofim").on("click",function(){
		updIdxOrder.updateOrders(orderedIds,function(data){
			//没有异常则保存成功
			if(!data){
				alert("保存成功");
			}
			$("#cacle").click();
		});
	});
	//重置
	$("#cacle").on("click",function(){
		//数据初始化
		$(".forOpt").attr("checked",false);
		$(".forOpt").next().html("");
		index = 0;
		orderedIds = [];
		
		//可操作项隐藏
		$("td:nth-child(1)").find("input").css("visibility","hidden");
		$("#cofim").fadeOut("fast");
		$("#cacle").fadeOut("fast");
	});
	
});