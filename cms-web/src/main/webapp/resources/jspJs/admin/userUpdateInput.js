$(function(){
	(function($) {
		//用户更新，选中用户已保存的角色和用户组
		//用户角色
		var $selRids = $("#selRids").val();
		$selRids = $selRids.substring(1,$selRids.length-1).split(",");
		$(":input[name='roleIds']").each(function(k){
			for(var i= 0;i<$selRids.length;i++){
				if($selRids[i].trim() == $(this).val()){
					$(this).attr("checked","checked");
				}
			}
		});
		//用户组
		var $selGids = $("#selGids").val();
		$selGids = $selGids.substring(1,$selGids.length-1).split(",");
		$(":input[name='groupIds']").each(function(){
			for(var i= 0;i<$selGids.length;i++){
				if($selGids[i].trim() == $(this).val()){
					$(this).attr("checked","checked");
				}
			}
		});
		
	})(jQuery);
});