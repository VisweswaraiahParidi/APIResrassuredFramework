package com.qa.gorest.utils;

public class StringUtils {
    public static String getRandonEmail() {
        return "api" + System.currentTimeMillis() + "@api.com";
    }
}
