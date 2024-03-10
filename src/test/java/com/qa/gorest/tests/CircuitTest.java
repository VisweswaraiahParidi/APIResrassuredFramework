package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIhttpStatus;
import com.qa.gorest.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CircuitTest extends BaseTest {

    @Test
    public void getAllCircuitUsersTest() {
        RestClient restClient = new RestClient(prop, baseURI);
       Response circuitResponse = restClient.get(CIRCUIT_ENDPOINT+"/2017/circuits.json",false,false);
       int statusCode = circuitResponse.getStatusCode();
        Assert.assertEquals(statusCode, APIhttpStatus.OK_200.getCode());
        JsonPathValidator js = new JsonPathValidator();
      List<String> country=  js.readList(circuitResponse, "$.MRData.CircuitTable.Circuits[?(@.circuitId=='shanghai')].Location.country");
        System.out.println("Country " +country);
        Assert.assertTrue(country.contains("China"));

//                .then().log().all()
//                .assertThat()
//                .statusCode(APIhttpStatus.OK_200.getCode());
    }
}
