package com.qa.gorest.base;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {

    public static final String GOREST_ENDPOINT = "/public/v2/users/";
    public static final String REQRES_ENDPOINT = "/api/users/";
    public static final String CIRCUIT_ENDPOINT = "/api/f1";
    public static final String AMADEUSTOKEN_ENDPOINT = "/v1/security/oauth2/token";
    public static final String AMADEUS_FLIGHT_BOOKING_ENDPOINT = "/v1/shopping/flight-destinations";

    protected ConfigurationManager config;
    protected Properties prop;
    protected RestClient restClient;
    protected String baseURI;


    @Parameters({"baseURI"})
    @BeforeTest
    public void setUp(String baseURI) {
        RestAssured.filters(new AllureRestAssured());
        config = new ConfigurationManager();
        prop = config.initializeProp();
        //  baseURI = prop.getProperty("baseURI");
        this.baseURI = baseURI;
     //   restClient = new RestClient(prop, baseURI);
    }

}
