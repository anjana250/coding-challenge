
package com.coding.challenge.contact.list.api.request;

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
    "name",
    "address",
    "phone",
    "email"
})
@Generated("jsonschema2pojo")
public class ContactListRequest {

    @JsonProperty("name")
    private RequestName name;
    @JsonProperty("address")
    private RequestAddress address;
    @JsonProperty("phone")
    private List<RequestPhone> phone = null;
    @JsonProperty("email")
    private String email;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ContactListRequest() {
    }

    /**
     * 
     * @param address
     * @param phone
     * @param name
     * @param email
     */
    public ContactListRequest(RequestName name, RequestAddress address, List<RequestPhone> phone, String email) {
        super();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @JsonProperty("name")
    public RequestName getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(RequestName name) {
        this.name = name;
    }

    public ContactListRequest withName(RequestName name) {
        this.name = name;
        return this;
    }

    @JsonProperty("address")
    public RequestAddress getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(RequestAddress address) {
        this.address = address;
    }

    public ContactListRequest withAddress(RequestAddress address) {
        this.address = address;
        return this;
    }

    @JsonProperty("phone")
    public List<RequestPhone> getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(List<RequestPhone> phone) {
        this.phone = phone;
    }

    public ContactListRequest withPhone(List<RequestPhone> phone) {
        this.phone = phone;
        return this;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public ContactListRequest withEmail(String email) {
        this.email = email;
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

    public ContactListRequest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
