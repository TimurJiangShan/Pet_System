package com.example.util;

import java.lang.reflect.Type;
import com.google.gson.Gson;


public class JsonUtil {

	public final static Gson gson = new Gson();
	
	/**
	 * object to json
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) {
		return gson.toJson(object);
	}
	
	/**
	 * json to object
	 * @param json
	 * @param object
	 * @return
	 */
	public static <T> T jsonToObject(String json,Class<T> object) {
		return gson.fromJson(json, object);
	}
	
	/**
	 * json to object
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T jsonToObject(String json,Type type) {
		return gson.fromJson(json, type);
	}
	
}
