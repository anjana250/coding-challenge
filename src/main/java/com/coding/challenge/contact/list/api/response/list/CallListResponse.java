
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


/**
 * Comment describing your JSON Schema
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "callList"
})
@Generated("jsonschema2pojo")
public class CallListResponse {

    @JsonProperty("callList")
    private List<Call> callList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public CallListResponse() {
    }

    /**
     * 
     * @param callList
     */
    public CallListResponse(List<Call> callList) {
        super();
        this.callList = callList;
    }

    @JsonProperty("callList")
    public List<Call> getCallList() {
        return callList;
    }

    @JsonProperty("callList")
    public void setCallList(List<Call> callList) {
        this.callList = callList;
    }

    public CallListResponse withCallList(List<Call> callList) {
        this.callList = callList;
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

    public CallListResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
