package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIhttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class GetUserTest extends BaseTest {

    @BeforeMethod
    public void getUserSetup(){
        restClient = new RestClient(prop,baseURI);
    }

    @Test(priority = 3)
    public void getAllUsersTest() {
        restClient.get(GOREST_ENDPOINT, true, true)
                .then().log().all()
                .assertThat()
                .statusCode(APIhttpStatus.OK_200.getCode());
    }

    @Test(enabled = false, priority = 2)
    public void getUserTest() {
        restClient = new RestClient(prop, baseURI);
        restClient.get(GOREST_ENDPOINT+"6756583",true, true)
                .then().log().all()
                .assertThat()
                .body("id", equalTo(6756583))
                .and()
                .statusCode(APIhttpStatus.OK_200.getCode());
    }

    @Test(priority = 1)
    public void getUserwithQueryparamsTest() {
        restClient = new RestClient(prop, baseURI);
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("name", "Viswa");
        queryParams.put("status", "active");

        restClient.get(GOREST_ENDPOINT,queryParams,null,true,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIhttpStatus.OK_200.getCode());
    }
}
