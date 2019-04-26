$(document).ready(function(){
	$(":input").attr("disabled", "disabled");
});


function refreshSwatch() {
	$SliderValue=$('#slider').slider( "value" );
	if($SliderValue==100){
		$('#slider').slider( "value", 86 );
		$(":input").removeAttr("disabled");
		$("#slider").unbind();
	}
}
$(function() {
	$("#slider").slider({
		change: refreshSwatch
	});
	// 上面说的你要是直接使用官方的ui js 文件, 要加入以代码
	// $("#slider").html('<span class="glyphicon glyphicon-arrow-right"></span>');
});