(function ($) {
	/**
	 * curr
	 * all
	 * count
	 */
    $.fn.pagination = function (curr, all, count) {
        if (all <= 0) {
            all = 1;
        }
        if (curr <= 0) {
            curr = 1;
        } else if (curr > all) {
            curr = all;
        }
        if (!count) {
            count = 10;
        } else if (count < 1) {
            count = 1;
        }
        var from = curr - parseInt(count / 2);
        var to = curr + parseInt(count / 2) + (count % 2) - 1;
        if (from <= 0) {
            from = 1;
            to = from + count - 1;
            if (to > all) {
                to = all;
            }
        }
        if (to > all) {
            to = all;
            from = to - count + 1;
            if (from <= 0) {
                from = 1;
            }
        }
        if (curr > 1) {
            var prev = $("<li><a href='"+url+"p=1'>&laquo;</a></li>");
            this.append(prev);
        }
        for (var i = from; i <= to; i++) {
            if (i == curr) {
                var li = $("<li class='active'><a href='"+url+"p="+i+"'>" + i + "</a></li>");
                this.append(li);
            } else {
                var li = $("<li><a href='"+url+"p="+i+"'>" + i + "</a></li>");
                this.append(li);
            }
        }
        if (curr < all) {
            var prev = $("<li><a href='"+url+"p="+all+"'>&raquo;</a></li>");
            this.append(prev);
        }
    }
})(jQuery);