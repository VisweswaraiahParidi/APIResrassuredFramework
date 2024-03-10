package com.qa.gorest.tests;

import com.google.j2objc.annotations.Property;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIhttpStatus;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;


public class AmadeusAPITest extends BaseTest {
    private String accessToken;

    @Parameters({"baseURI", "grantType", "clientId", "clientSecrete"})
    @BeforeMethod
    public void flightAPISetup(String baseURI, String grantType, String clientId, String clientSecrete) {
        restClient = new RestClient(prop, baseURI);
        accessToken = restClient.getAccessToken(AMADEUSTOKEN_ENDPOINT, grantType, clientId, clientSecrete);
    }

    @Test
    public void getFlightInfoTest() {

        RestClient restClientFlight = new RestClient(prop, baseURI);
        Map<String, Object> queryParams = new HashMap<String, Object>() ;
        queryParams.put("origin", "PAR");
        queryParams.put("maxPrice", 200);

        Map<String, String> headersMap = new HashMap<String, String>() ;
        headersMap.put("Authorization", "Bearer " + accessToken);
       Response flightresponse=  restClientFlight.get(AMADEUS_FLIGHT_BOOKING_ENDPOINT,queryParams,headersMap, false,true)

                .then().log().all()
                .assertThat()
                .statusCode(APIhttpStatus.OK_200.getCode())
                .extract()
                .response();

        JsonPath js = flightresponse.jsonPath();
        String type = js.get("data[0].type");
        System.out.println(type);


    }
}
