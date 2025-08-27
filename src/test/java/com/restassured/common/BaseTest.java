package com.restassured.common;

import com.restassured.data.LoginPoJO_Builder;
import com.restassured.globals.ConfigsGlobal;
import com.restassured.globals.EndPointGlobal;
import com.restassured.globals.TokenGlobal;
import com.restassured.helpers.PropertiesHelper;
import com.restassured.keywords.ApiKeyword;
import com.restassured.listeners.TestListener;
import com.restassured.model.LoginPOJO;
import com.google.gson.Gson;
import com.restassured.reports.AllureManager;
import com.restassured.utils.LogUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeTest
    public void loginUser() {
        LoginPOJO loginPOJO = LoginPoJO_Builder.getDataLogin();

        Gson gson = new Gson();

        Response response = ApiKeyword.postNotAuth(EndPointGlobal.EP_LOGIN, gson.toJson(loginPOJO));

        response.then().statusCode(200);

        TokenGlobal.TOKEN = response.getBody().path("token");
        LogUtils.info("Token Global: " + TokenGlobal.TOKEN);
        AllureManager.saveTextLog("Token Global: " + TokenGlobal.TOKEN);
    }
}