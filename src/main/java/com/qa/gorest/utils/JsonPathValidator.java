package com.qa.gorest.utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.FrameworkException.APIFrameworkException;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class JsonPathValidator {

    private String getJsonResponseasString(Response response) {
        return response.getBody().asString();
    }

    public <T> T read(Response response, String jsonPath) {

        String jsonResponse = getJsonResponseasString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
            throw new APIFrameworkException(jsonPath + "is not found");
        }
    }

    public <T> List<T> readList(Response response, String jsonPath) {

        String jsonResponse = getJsonResponseasString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
            throw new APIFrameworkException(jsonPath + "is not found");
        }
    }

    public <T> List<Map<String, T>> readListOfMaps(Response response, String jsonPath) {

        String jsonResponse = getJsonResponseasString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
            throw new APIFrameworkException(jsonPath + "is not found");
        }
    }
}
