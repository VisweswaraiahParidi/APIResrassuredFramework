package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIhttpStatus;
import com.qa.gorest.pojo.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.qa.gorest.utils.StringUtils.getRandonEmail;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class APISChemaValidators extends BaseTest {

    @BeforeMethod
    public void createUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }
    @Test
    public void createUserAPISChemaTest() {
        User user = new User("TOM", getRandonEmail(), "male", "active");

        //Post
        //  restClient = new RestClient();
        restClient.post(GOREST_ENDPOINT, "json", user, true, true)
                .then().log().all()
                .assertThat()
                .statusCode(APIhttpStatus.CREATED_201.getCode())
                .body(matchesJsonSchemaInClasspath("resources/createschemevalidator.json"));


    }
}
