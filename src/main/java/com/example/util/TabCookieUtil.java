package com.example.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TabCookieUtil {

	private TabCookieUtil() {}
	
	public static void setTab(String ptab,HttpServletResponse response) {
		CookieAndSessionUtil.setCookie("tab", Base64Util.encode(ptab), 30 * 24 * 60 * 60, "/", true, response);
	}
	
	public static void editPtab(HttpServletRequest request,HttpServletResponse response,String ptab) {
		CookieAndSessionUtil.editCookie(request,response, "tab", Base64Util.encode(ptab));
	}
	
	public static String getTab(HttpServletRequest request,HttpServletResponse response,String ptab) {
		String str = null;
		str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "tab"));
		if(str == null) {
			if(ptab.equals("def")) {
				setTab("all",response);
				return str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "tab"));
			}else {
				setTab(ptab,response);
				return str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "tab"));
			}
		}else {
			if(ptab.equals("def")) {
				return str;
			}else if(ptab.equals(str)) {
				return str;
			}else {
				editPtab(request,response,ptab);
				return str = Base64Util.decode(CookieAndSessionUtil.getCookie(request, "tab"));
			}
		}

	}
	
	public static void removePtab(HttpServletResponse response) {
		CookieAndSessionUtil.removeCookie(response, "tab", "/", true);
	}
}
