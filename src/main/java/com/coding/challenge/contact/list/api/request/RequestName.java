
package com.coding.challenge.contact.list.api.request;

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
    "first",
    "middle",
    "last"
})
@Generated("jsonschema2pojo")
public class RequestName {

    @JsonProperty("first")
    private String first;
    @JsonProperty("middle")
    private String middle;
    @JsonProperty("last")
    private String last;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestName() {
    }

    /**
     * 
     * @param middle
     * @param last
     * @param first
     */
    public RequestName(String first, String middle, String last) {
        super();
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    @JsonProperty("first")
    public String getFirst() {
        return first;
    }

    @JsonProperty("first")
    public void setFirst(String first) {
        this.first = first;
    }

    public RequestName withFirst(String first) {
        this.first = first;
        return this;
    }

    @JsonProperty("middle")
    public String getMiddle() {
        return middle;
    }

    @JsonProperty("middle")
    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public RequestName withMiddle(String middle) {
        this.middle = middle;
        return this;
    }

    @JsonProperty("last")
    public String getLast() {
        return last;
    }

    @JsonProperty("last")
    public void setLast(String last) {
        this.last = last;
    }

    public RequestName withLast(String last) {
        this.last = last;
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

    public RequestName withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
