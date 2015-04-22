package com.example.dfa;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONConverter {

	public static String fromJSon(String json_string, Object o)
			throws JSONException, IllegalAccessException,
			IllegalArgumentException, InstantiationException,
			ClassNotFoundException, InvocationTargetException {
		JSONObject object = new JSONObject(json_string);
		Field[] fields = o.getClass().getFields();
		System.out.println("fields len=" + fields.length);
		Method[] methods = o.getClass().getMethods();
		for (Field field : fields) {
			if (field.isAnnotationPresent(JSONValue.class)) {
				JSONValue jv = field.getAnnotation(JSONValue.class);
				String tag = jv.tag();

				if (tag.length() > 0) {
					if (field.getType().getSimpleName().equals("Int")) {
						field.setInt(o, object.getInt(tag));
					} else if (field.getType().getSimpleName().equals("String")) {
						field.set(o, object.getString(tag));
					} else if (field.getType().getSimpleName().equals("List")) {
						String className = null;
						String packageName = null;
						if (tag.lastIndexOf('s') == tag.length() - 1) {
							className = tag.substring(0, tag.length() - 1);
							className = firstToUcase(className);
							packageName = o.getClass().getPackage().getName()
									+ '.' + className;
						}
						if (packageName != null) {
							Class classInfo = Class.forName(packageName);
							JSONArray array = object.getJSONArray(tag);
							for (int i = 0; i < array.length(); i++) {
								Object itemObject = classInfo.newInstance();
								String itemString = array.getString(i);
								fromJSon(itemString, itemObject);
								for (Method method : methods) {
									if (method
											.isAnnotationPresent(JSONMethod.class)) {
										JSONMethod jm = method
												.getAnnotation(JSONMethod.class);
										String tagMethod = jm.tag();
										if (tagMethod.equals("addList")) {
											method.invoke(o, itemObject);
										}

									}
								}
							}
						}

					} else {
						Object v = field.get(o);
						fromJSon(object.getString(tag), v);
					}

				}
			}
		}
		return object.toString();
	}

	public static String firstToUcase(String s) {
		byte[] items = s.getBytes();
		items[0] = (byte) ((char) items[0] - ('a' - 'A'));
		String newStr = new String(items);
		return newStr;
	}

	public static String toJSon(Object o) throws IllegalAccessException,
			IllegalArgumentException, JSONException {
		JSONObject jo = new JSONObject();
		Field[] fields = o.getClass().getFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(JSONValue.class)) {
				JSONValue jv = f.getAnnotation(JSONValue.class);
				String tag = jv.tag();
				System.out.println("tag=" + tag);
				if (tag.length() > 0) {
					if (f.getType().getSimpleName().equals("int")) {
						jo.put(tag, f.getInt(o));
					} else {
						Object v = f.get(o);
						if (v != null) {
							jo.put(tag, v);
						}
					}
				}
			}
		}
		return jo.toString();
	}
}