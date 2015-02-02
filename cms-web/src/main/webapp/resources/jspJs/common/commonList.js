$(function(){
	(function($) {
		//删除对象确认
		$(".deleteObj").click(function(){
			if(!confirm("此操作不可逆，确认删除吗？")){
				return false;
			}
		});
	})(jQuery);
});