package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIhttpStatus;
import org.testng.annotations.Test;

public class ReqResAPITest extends BaseTest {
    @Test
    public void getAllReqResUsersTest() {
        RestClient restClient = new RestClient(prop, baseURI);
        restClient.get(REQRES_ENDPOINT+"/2",false,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIhttpStatus.OK_200.getCode());
    }
}
