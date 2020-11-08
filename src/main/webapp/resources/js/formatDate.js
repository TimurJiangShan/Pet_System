function formatDate(dateTimeStamp){   

	        var minute=1000*60;
	        var  hour=minute*60;
	        var day=hour*24;
	        var week=day*7;
	        var halfamonth=day*15;
	        var month=day*30;

	        var  now=new Date().getTime();
	        var diffValue=now - dateTimeStamp;

	        if(diffValue<0){return;}

	        var  minC=diffValue / minute;
	        var  hourC=diffValue / hour;
	        var  dayC=diffValue / day;
	        var  weekC=diffValue / week;     
	        var  monthC=diffValue / month;

	        if(monthC>=1){
	        	result="" + parseInt(monthC) + "months ago";
	        }
	        else if(weekC>=1){
	        	result="" + parseInt(weekC) + "weeks ago";
	        }
	        else if(dayC>=1){
	        	result=""+ parseInt(dayC) +"days ago";
	        }
	        else if(hourC>=1){
	        	result=""+ parseInt(hourC) +"hours ago";
	        }
	        else if(minC>=1){
	        	result=""+ parseInt(minC) +"minutes ago";
	        }else
	        result="just now";
	        return result;
	    };