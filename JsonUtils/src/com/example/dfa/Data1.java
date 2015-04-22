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

    /**
     * 
     * @return
     *     The ims
     */
    public List<Im> getIms() {
        return ims;
    }

    /**
     * 
     * @param ims
     *     The ims
     */
    public void setIms(List<Im> ims) {
        this.ims = ims;
    }

    /**
     * 
     * @return
     *     The tm
     */
    public Tm getTm() {
        return tm;
    }

    /**
     * 
     * @param tm
     *     The tm
     */
    public void setTm(Tm tm) {
        this.tm = tm;
    }

    /**
     * 
     * @return
     *     The passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 
     * @param passWord
     *     The passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 
     * @return
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
	@JSONMethod(tag = "addList")
    public void addList(Im im)
    {
		ims.add(im);
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ims).append(tm).append(passWord).append(userName).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data1) == false) {
            return false;
        }
        Data1 rhs = ((Data1) other);
        return new EqualsBuilder().append(ims, rhs.ims).append(tm, rhs.tm).append(passWord, rhs.passWord).append(userName, rhs.userName).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
