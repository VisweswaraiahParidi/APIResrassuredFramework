package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIConstants;
import com.qa.gorest.constants.APIhttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.ExcelUtil;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.qa.gorest.utils.StringUtils.getRandonEmail;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest {
    @BeforeMethod
    public void createUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }

    @DataProvider
    public Object[][] getUserdata() {
        return new Object[][]{
                {"Viswa", "male", "active"},
                {"Ramana", "male", "active"},
                {"Bhaghavan", "male", "active"}
        };
    }

    @DataProvider
    public Object[][] getUserdatasheetData() {
        return ExcelUtil.getTestData(APIConstants.GOREST_DATA_SHEET_NAME);

    }

    @Test(dataProvider = "getUserdatasheetData")
    public void createUserTest(String name, String gender, String status) {
        User user = new User(name, getRandonEmail(), gender, status);

        //Post
        //  restClient = new RestClient();
        Integer userId = restClient.post(GOREST_ENDPOINT, "json", user, true, true)
                .then().log().all()
                .assertThat()
                .statusCode(APIhttpStatus.CREATED_201.getCode())
                .and()
                .extract()
                .path("id");

        System.out.println("ID: " + userId);

        //Get
        restClient.get(GOREST_ENDPOINT + userId, false, true)
                .then().log().all()
                .assertThat().log().all()
                .statusCode(APIhttpStatus.OK_200.getCode())
                .and()
                .assertThat().body("id", equalTo(userId));


    }


}
