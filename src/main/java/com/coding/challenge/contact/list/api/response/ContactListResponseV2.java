
package com.coding.challenge.contact.list.api.response;

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


/**
 * Comment describing your JSON Schema
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contactList"
})
@Generated("jsonschema2pojo")
public class ContactListResponseV2 {

    @JsonProperty("contactList")
    private List<Contact> contactList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ContactListResponseV2() {
    }

    /**
     * 
     * @param contactList
     */
    public ContactListResponseV2(List<Contact> contactList) {
        super();
        this.contactList = contactList;
    }

    @JsonProperty("contactList")
    public List<Contact> getContactList() {
        return contactList;
    }

    @JsonProperty("contactList")
    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public ContactListResponseV2 withContactList(List<Contact> contactList) {
        this.contactList = contactList;
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

    public ContactListResponseV2 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
