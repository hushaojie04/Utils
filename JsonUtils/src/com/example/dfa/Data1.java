package com.example.dfa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Data1 {
	@JSONValue(tag = "ims")
	public List<Im> ims = new ArrayList<Im>();
	@JSONValue(tag = "tm")
	public Tm tm = new Tm();
	@JSONValue(tag = "passWord")
	public String passWord;
	@JSONValue(tag = "userName")
	public String userName;
	public Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JSONMethod(tag = "[ims-im]")
	public void addList(Im im) {
		ims.add(im);
	}

}
