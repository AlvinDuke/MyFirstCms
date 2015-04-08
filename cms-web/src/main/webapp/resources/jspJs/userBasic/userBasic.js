$(function(){
	//导航栏子栏目显示
	$(".nav-cates").on("click",function(event){
		event.stopPropagation();
		//全部隐藏
		$(".sub-cates-holder").hide(100);
		//对应的子栏目显示
		$("#sub"+this.id).slideDown(500);
	});
	
	//点击其他地方隐藏
	$("body").on("click",function(){
		//全部隐藏
		$(".sub-cates-holder").hide(100);
	});
});