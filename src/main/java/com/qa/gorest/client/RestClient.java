package com.qa.gorest.client;

import com.qa.gorest.FrameworkException.APIFrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RestClient {

    //    public static final String BASE_URI = "https://gorest.co.in";
//    public static final String BEARER_TOKEN = "84401bc3bc5e2259f0ac5820f121ecab97ab9be7c1337c03936a89cbe51a4794";
    private static RequestSpecBuilder specBuilder;
    private Properties prop;
    private String baseURI;
    private boolean isAuhtorizationTokenAdded = false;

//    static {
//        specBuilder = new RequestSpecBuilder();
//    }

    public RestClient(Properties prop, String baseURI) {
        specBuilder = new RequestSpecBuilder();
        this.prop = prop;
        this.baseURI = baseURI;
    }

    public void addAuthorizationHeader() {
        if (!isAuhtorizationTokenAdded) {
            specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
            isAuhtorizationTokenAdded = true;
        }
    }

    private void setRequestContentType(String contentType) {
        switch (contentType.toLowerCase()) {
            case "json":
                specBuilder.setContentType(ContentType.JSON);
                break;

            case "xml":
                specBuilder.setContentType(ContentType.XML);
                break;

            case "text":
                specBuilder.setContentType(ContentType.TEXT);
                break;

            case "multipart":
                specBuilder.setContentType(ContentType.MULTIPART);
                break;

            default:
                System.out.println("Please pass correct content Type");
                throw new APIFrameworkException("INVALIDCONTENTTTTYPE");
        }
    }

    private RequestSpecification createRequestSpec(boolean authReq) {

        specBuilder.setBaseUri(baseURI);
        if (authReq) {
            addAuthorizationHeader();
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap) {

        specBuilder.setBaseUri(baseURI);
        addAuthorizationHeader();
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, Object> queryParams, boolean isAuthreq) {

        specBuilder.setBaseUri(baseURI);
        if (isAuthreq) {
            addAuthorizationHeader();}
            if (headersMap != null) {
                specBuilder.addHeaders(headersMap);
            }
            if (queryParams != null) {
                specBuilder.addQueryParams(queryParams);
            }

        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, Object> queryParams) {

        specBuilder.setBaseUri(baseURI);
        addAuthorizationHeader();
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (queryParams != null) {
            specBuilder.addQueryParams(queryParams);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Object requestBody, String contentType,  boolean isAuthReq) {
if( isAuthReq){
        specBuilder.setBaseUri(baseURI);}
        addAuthorizationHeader();
        setRequestContentType(contentType);
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap) {

        specBuilder.setBaseUri(baseURI);
        addAuthorizationHeader();
        setRequestContentType(contentType);
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        return specBuilder.build();
    }

    //Https Method utils

    /*****GET*****/
    public Response get(String serviceUrl, boolean authReq, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(authReq)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(authReq)).when().get(serviceUrl);
    }

    public Response get(String serviceUrl, Map<String, String> headersMap, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(headersMap)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap)).when().get(serviceUrl);
    }

    public Response get(String serviceUrl, Map<String, Object> queryParams, Map<String, String> headersMap, boolean isAuthReq, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(headersMap, queryParams, isAuthReq)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(headersMap, queryParams, isAuthReq)).when().get(serviceUrl);
    }


    /*****POST*****/

    public Response post(String serviceUrl, String contentType, Object requestBody,  boolean isAuthReq, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType,isAuthReq)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody, contentType,isAuthReq)).when().post(serviceUrl);
    }

    public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().post(serviceUrl);
    }


    /*****PUT*****/
    public Response put(String serviceUrl, String contentType, Object requestBody,  boolean isAuthReq, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType,isAuthReq)).log().all()
                    .when()
                    .put(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody, contentType,isAuthReq)).when().put(serviceUrl);
    }

    public Response put(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
                    .when()
                    .put(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().put(serviceUrl);
    }

    /*****PATCH*****/
    public Response patch(String serviceUrl, String contentType, Object requestBody, boolean isAuthReq, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType,isAuthReq)).log().all()
                    .when()
                    .patch(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody, contentType,isAuthReq)).when().patch(serviceUrl);
    }

    public Response patch(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
                    .when()
                    .patch(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().patch(serviceUrl);
    }

    /*****DELETE*****/
    public Response delete(String serviceUrl, boolean authReq, boolean log) {
        if (log) {
            return RestAssured.given(createRequestSpec(authReq)).log().all()
                    .when()
                    .delete(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(authReq)).when().delete(serviceUrl);
    }

    public String getAccessToken(String serviceUrl, String grantType, String clientId, String clientSecrete) {
        // POST to get the token
        RestAssured.baseURI = "https://test.api.amadeus.com";

        String accessToken = given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("grant_type", grantType)
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecrete)
                .when()
                .post(serviceUrl)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("access_token");

        System.out.println("Access Token: " + accessToken);

        return accessToken;

    }
}
