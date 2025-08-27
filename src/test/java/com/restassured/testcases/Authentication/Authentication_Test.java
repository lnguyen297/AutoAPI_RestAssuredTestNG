package com.restassured.testcases.Authentication;

import com.google.gson.Gson;
import com.restassured.common.BaseTest;
import com.restassured.dataproviders.DataProviderFactory;
import com.restassured.globals.EndPointGlobal;
import com.restassured.keywords.ApiKeyword;
import com.restassured.model.RegisterUser_POJO;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Hashtable;

public class Authentication_Test extends BaseTest {

    int USER_ID;

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Regression Test")
    @Feature("Authentication")
    @Story("Test Create User")
    @Description("Verify that user can be created successfully with valid data")
    @Link("https://jira.com/anhtester/apitest/authentication/10")
    @Test(dataProvider = "registerDataSuccess", dataProviderClass = DataProviderFactory.class)
    public void testCreateUser(Hashtable<String, String> data){

        // Build request body từ DataProvider
        RegisterUser_POJO registerUserPojo = new RegisterUser_POJO();
        registerUserPojo.setUsername(data.get("USERNAME"));
        registerUserPojo.setFirstname(data.get("FIRSTNAME"));
        registerUserPojo.setLastname(data.get("LASTNAME"));
        registerUserPojo.setEmail(data.get("EMAIL"));
        registerUserPojo.setPassword(data.get("PASSWORD"));
        registerUserPojo.setPhone(data.get("PHONE"));
        registerUserPojo.setUserStatus(Integer.parseInt(data.get("USERSTATUS")));

        // Chuyển POJO thành JSON String
        Gson gson = new Gson();
        String requestBody = gson.toJson(registerUserPojo);

        // Gọi API
        Response response = ApiKeyword.post(EndPointGlobal.EP_REGISTER, requestBody);

        // Verify status code
        ApiKeyword.verifyStatusCode(response, 200);

        // Verify response fields
        ApiKeyword.verifyKeyValue( response, data.get("USERNAME"), "response.username");
        ApiKeyword.verifyKeyValue( response, data.get("FIRSTNAME"), "response.firstName");
        ApiKeyword.verifyKeyValue( response, data.get("LASTNAME"), "response.lastName");
        ApiKeyword.verifyKeyValue( response, data.get("EMAIL"), "response.email");
        ApiKeyword.verifyKeyValue( response, data.get("PHONE"), "response.phone");
        ApiKeyword.verifyKeyValue( response, data.get("USERSTATUS"), "response.userStatus");

        // Verify userId được trả về (không null)
        ApiKeyword.verifyIdNotNull(response, "response.id");
    }

    }
