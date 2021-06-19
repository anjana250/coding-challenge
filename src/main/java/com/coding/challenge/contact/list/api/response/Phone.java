
package com.coding.challenge.contact.list.api.response;

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
public class Phone {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    private Phone.Category type;
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
    public Phone() {
    }

    /**
     * 
     * @param type
     * @param number
     */
    public Phone(Phone.Category type, String number) {
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
    public Phone.Category getType() {
        return type;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(Phone.Category type) {
        this.type = type;
    }

    public Phone withType(Phone.Category type) {
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
    public void setNumber(String number) {
        this.number = number;
    }

    public Phone withNumber(String value) {
        this.number = number;
        return this;
    }

    @Generated("jsonschema2pojo")
    public enum Category {

        HOME("home"),
        WORK("work"),
        MOBILE("mobile");
        private final String value;
        private final static Map<String, Phone.Category> CONSTANTS = new HashMap<String, Phone.Category>();

        static {
            for (Phone.Category c: values()) {
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
        public static Phone.Category fromValue(String value) {
            Phone.Category constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
