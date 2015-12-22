$(document).ready(function(){
	/**============检索功能伸缩===Begin========*/
	$("#divSearchTitle").css("cursor","pointer");
	$("#divSearchTitle").click(function(){
   		$("#divSearch").slideToggle("slow");
   		if($("#divSlide").attr("class")=="glyphicon glyphicon-chevron-up"){
   			$("#divSlide").removeClass("glyphicon-chevron-up").addClass('glyphicon-chevron-down');
   		}else{
   			$("#divSlide").removeClass("glyphicon-chevron-down").addClass('glyphicon-chevron-up'); 
   		}
	});
	/**============检索功能伸缩===End========*/

	/**============清空文本===Begin========*/
	$('#btnClear').click(function(){
	     $("input[type='text']").val("");
	});
	/**============清空文本===End========*/
	
});