$(function(){
	$("#enterCode").keyup(function(event){
		if(event.keyCode==13){
			if(this.value == null || this.value == ""){
				alert("贵司未受邀请，谢谢");
				return false;
			}
			$.get('getPfl?site='+ this.value, function(data) {
				if(data!=0){
					$("#valid-div").fadeOut(500);
					$("#coverDiv").fadeIn(1400,function(){
						$("#coverDiv").fadeOut(1400,function(){
							$("body").html(data);
							$("body").hide();
							$("body").fadeIn(500);
						});
					});
				}
				else{
					alert("贵司未受邀请，谢谢");
				}
			});
		}
	});
});