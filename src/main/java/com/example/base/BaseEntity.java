package com.example.base;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class BaseEntity {

	private static final long MINUTE = 60 * 1000;
	private static final long HOUR = 60 * MINUTE;
	private static final long DAY = 24 * HOUR;
	private static final long WEEK = 7 * DAY;
	private static final long MONTH = 31 * DAY;
	private static final long YEAR = 12 * MONTH;

	//format date
	public static String formatDate(Date date) throws Exception {
		
		String str = "months ago";
		byte[] bytes = str.getBytes();
		str = new String(bytes,"UTF-8");
		
		String str2 = "weeks ago";
		byte[] bytes2 = str2.getBytes();
		str2 = new String(bytes2,"UTF-8");
		
		if (date == null)
			return "";

		long offset = System.currentTimeMillis() - date.getTime();
		if (offset > YEAR) {
			return (offset / YEAR) + "years ago";
		} else if (offset > MONTH) {
			return (offset / MONTH) + str;
		} else if (offset > WEEK) {
			return (offset / WEEK) + str2;
		} else if (offset > DAY) {
			return (offset / DAY) + "days ago";
		} else if (offset > HOUR) {
			return (offset / HOUR) + "hours ago";
		} else if (offset > MINUTE) {
			return (offset / MINUTE) + "min ago";
		} else {
			return "just now";
		}
	}
}
