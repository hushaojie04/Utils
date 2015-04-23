package com.azt.Utils.Json;

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
        if (json_string.equals("") || json_string == null) {
            return "";
        }
        JSONObject object = new JSONObject(json_string);
        return fromJSon(object, o);
    }

    public static String fromJSon(JSONObject object, Object o)
            throws JSONException, IllegalAccessException,
            IllegalArgumentException, InstantiationException,
            ClassNotFoundException, InvocationTargetException {
        if (object == null || object.equals("")  ) {
            return "";
        }
        Field[] fields = o.getClass().getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(JSONValue.class)) {
                JSONValue jv = field.getAnnotation(JSONValue.class);
                String tag = jv.tag();

                if (tag.length() > 0) {
                    if (field.getType().getSimpleName().equalsIgnoreCase("Int")) {
                        field.setInt(o, object.getInt(tag));
                    } else if (field.getType().getSimpleName()
                            .equalsIgnoreCase("boolean")) {
                        field.set(o, object.getBoolean(tag));
                    } else if (field.getType().getSimpleName()
                            .equalsIgnoreCase("String")) {
                        field.set(o, object.optString(tag));
                    } else if (field.getType().getSimpleName()
                            .equalsIgnoreCase("List")) {
                        String[] names = new String[2];
                        createFieldClassAndPackageName(names, o, tag);
                        //添加List Item的接口
                        JSONMethodInfo methodInfo = createMethod(names, o, tag);
                        System.out.println("methodInfo.type=" + methodInfo.type);
                        JSONArray array = object.getJSONArray(tag);
                        if (methodInfo.type.equalsIgnoreCase("String")) {
                            //如果是Item是String类型，就直接List add
                            for (int i = 0; i < array.length(); i++) {
                                String itemString = array.optString(i);
                                methodInfo.mMethod.invoke(o, itemString);
                            }
                        } else {
                            //如果不是Item是其他类型，new Item 并List add ,然后解析
                            Class classInfo = Class.forName(names[1]);
                            for (int i = 0; i < array.length(); i++) {
                                Object itemObject = classInfo.newInstance();
                                String itemString = array.optString(i);
                                fromJSon(itemString, itemObject);
                                methodInfo.mMethod.invoke(o, itemObject);
                            }

                        }

                    } else {
                        Object v = field.get(o);
                        tag = object.optString(tag);
                        if (tag.equals("") || tag == null || tag.equals("null")) {

                        } else {
                            fromJSon(tag, v);
                        }
                    }

                }
            }
        }
        return object.toString();
    }

    public static JSONMethodInfo createMethod(String[] fieldClsAndPgkNames,
                                              Object object, String tag) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        JSONMethodInfo methodInfo = new JSONMethodInfo();
        if (fieldClsAndPgkNames[1] != null) {
            Method[] methods = object.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(JSONMethod.class)) {
                    JSONMethod jm = method.getAnnotation(JSONMethod.class);
                    String tagMethod = jm.tag();
                    if (methodInfo.parseTagMethodFormat(tag, tagMethod)) {
                        methodInfo.mMethod = method;
                        return methodInfo;
                    }
                }

            }
            return null;
        }
        return null;

    }

    private static void createFieldClassAndPackageName(String[] names,
                                                       Object object, String tag) {
        String className = null;
        String packageName = null;
        if (tag != null && !tag.equals("")) {
            className = Inflector.getInstance().singularize(tag);
            className = firstToUcase(className);
            packageName = object.getClass().getPackage().getName() + '.'
                    + className;
            names[0] = className;
            names[1] = packageName;
        } else {
            throw new NullPointerException();
        }
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