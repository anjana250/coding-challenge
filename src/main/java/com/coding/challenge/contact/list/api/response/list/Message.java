
package com.coding.challenge.contact.list.api.response.list;

import java.util.HashMap;
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
    "messageCode",
    "messageDescription"
})
@Generated("jsonschema2pojo")
public class Message {

    @JsonProperty("messageCode")
    private String messageCode;
    @JsonProperty("messageDescription")
    private String messageDescription;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Message() {
    }

    /**
     * 
     * @param messageCode
     * @param messageDescription
     */
    public Message(String messageCode, String messageDescription) {
        super();
        this.messageCode = messageCode;
        this.messageDescription = messageDescription;
    }

    @JsonProperty("messageCode")
    public String getMessageCode() {
        return messageCode;
    }

    @JsonProperty("messageCode")
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Message withMessageCode(String messageCode) {
        this.messageCode = messageCode;
        return this;
    }

    @JsonProperty("messageDescription")
    public String getMessageDescription() {
        return messageDescription;
    }

    @JsonProperty("messageDescription")
    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public Message withMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
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

    public Message withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
