function showScroll() {
	$(window).scroll(function() {
		var scrollValue = $(window).scrollTop();
		//console.log(scrollValue);
		if (scrollValue > 200) {
			$('#back2Top').css("display", "flex");
		} else {
			$('#back2Top').css("display", "none");
		}
	});
}
showScroll();
$("#back2Top").click(function() {
	//back to top slowly
	$('body,html').animate({
		scrollTop : 0
	}, 500);
	return false;
	//back to top directly
	//window.scroll(0,0);
});