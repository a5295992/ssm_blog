$(document).ready(function() {
	init();

});
function init() {
	$(".name,.pwd,#valid").keyup(function(event) {
		if (event.keyCode == 13) {
			$('#login').click();
		}
	});
	
	$("#login").click(function(){
		$("#login_form").submit();
	});
	
	//定时 跳转
	
	
	
	var changePic = function changePic(){
		$('#verifyCodePic')[0].src = headpath+'/getValidateImage?name=' + Math.random();
		return false;
	};
	
	$('#changeimg').click(changePic);
}
















