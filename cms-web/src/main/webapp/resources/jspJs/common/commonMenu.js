//上下文根路径
var root = $("#root").val();
$(function(){
	(function($) {
		//后台管理，选中模块，左侧导航栏添加选中样式
		var seldmodl = $("#seldModlName").val();
		//若左侧导航栏的a标签的href中含有选中模块的字符串，则对应的li标记添加选中样式
		$("#left-menu").find("a").each(function(){
			var thisHref = $(this).attr("href");
			if(thisHref.indexOf(seldmodl)!=-1){
				$(this).parent().addClass("active");
			}
		});
	})(jQuery);
});