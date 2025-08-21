package com.restassured.common;

import com.restassured.data.LoginPoJO_Builder;
import com.restassured.globals.ConfigsGlobal;
import com.restassured.globals.TokenGlobal;
import com.restassured.helpers.PropertiesHelper;
import com.restassured.model.LoginPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeSuite
    public void setupSuite() {
        PropertiesHelper.loadAllFiles();
    }

    @BeforeTest
    public void loginUser() {
       // LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        LoginPOJO loginPOJO = LoginPoJO_Builder.getDataLogin();
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT)
                .contentType(ConfigsGlobal.CONTENT_TYPE)
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        response.then().statusCode(200);

        TokenGlobal.TOKEN = response.getBody().path("token");
        System.out.println("Token Global: " + TokenGlobal.TOKEN);
    }
}