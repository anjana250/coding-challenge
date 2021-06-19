package com.coding.challenge.contact.list.utils;

public class StringUtils {

    public static boolean isNotNullOrEmpty(String input){
        return null != input && input.trim().length() >0;
    }
}
