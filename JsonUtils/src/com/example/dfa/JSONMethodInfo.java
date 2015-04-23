package com.azt.Utils.Json;

import org.json.JSONArray;

import java.lang.reflect.Method;

public class JSONMethodInfo {
	Method mMethod;
	String type;

	// "[ims-im]"
	// [+fieldTag+ classType]
	boolean parseTagMethodFormat(String fieldTag, String methodTag) {
		if (methodTag.contains(fieldTag)) {
			type = methodTag.substring(methodTag.indexOf('-') + 1,
					methodTag.length()-1);
			return true;
		}
		return false;
	}

}
