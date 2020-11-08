$(function(){
	$(window).scroll( function(){ 
     var scrollValue=$(window).scrollTop();
		if (scrollValue > 200) {
			$('#back2Top').css("display","flex");
		} else {
			$('#back2Top').css("display","none");
		}
	});	
  
  $("#back2Top").click(function(){
		$('body,html').animate({scrollTop:0},500);
		return false;
	});

});