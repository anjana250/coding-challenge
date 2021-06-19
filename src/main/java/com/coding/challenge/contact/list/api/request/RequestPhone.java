
package com.coding.challenge.contact.list.api.request;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "number"
})
@Generated("jsonschema2pojo")
public class RequestPhone {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    private RequestPhone.Category type;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("number")
    private String number;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestPhone() {
    }

    /**
     * 
     * @param type
     * @param number
     */
    public RequestPhone(RequestPhone.Category type, String number) {
        super();
        this.type = type;
        this.number = number;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public RequestPhone.Category getType() {
        return type;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setCategory(RequestPhone.Category type) {
        this.type = type;
    }

    public RequestPhone withType(RequestPhone.Category type) {
        this.type = type;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("number")
    public void setValue(String number) {
        this.number = number;
    }

    public RequestPhone withNumber(String number) {
        this.number = number;
        return this;
    }

    @Generated("jsonschema2pojo")
    public enum Category {

        HOME("home"),
        WORK("work"),
        MOBILE("mobile");
        private final String value;
        private final static Map<String, RequestPhone.Category> CONSTANTS = new HashMap<String, RequestPhone.Category>();

        static {
            for (RequestPhone.Category c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Category(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static RequestPhone.Category fromValue(String value) {
            RequestPhone.Category constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
