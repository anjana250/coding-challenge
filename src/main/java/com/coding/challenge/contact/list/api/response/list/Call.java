
package com.coding.challenge.contact.list.api.response.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "phone",
    "messageList"
})
@Generated("jsonschema2pojo")
public class Call {

    @JsonProperty("name")
    private Name name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("messageList")
    private List<Message> messageList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Call() {
    }

    /**
     * 
     * @param messageList
     * @param phone
     * @param name
     */
    public Call(Name name, String phone, List<Message> messageList) {
        super();
        this.name = name;
        this.phone = phone;
        this.messageList = messageList;
    }

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    public Call withName(Name name) {
        this.name = name;
        return this;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Call withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @JsonProperty("messageList")
    public List<Message> getMessageList() {
        return messageList;
    }

    @JsonProperty("messageList")
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Call withMessageList(List<Message> messageList) {
        this.messageList = messageList;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Call withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
