package com.restassured.testcases.Category;

import com.restassured.common.BaseTest;
import com.restassured.globals.EndPointGlobal;
import com.restassured.keywords.ApiKeyword;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Category_Test_2{

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Regression Test")
    @Feature("Category Management")
    @Story("Get All Category")
    @Description("Verify that user can get all categories successfully")
    @Link("https://jira.com/anhtester/apitest/category/20")
    @Test
    public void getAllCategory(){
        Response response = ApiKeyword.getNotAuth(EndPointGlobal.EP_CATEGORY);

        //Verify status code
        ApiKeyword.verifyFailureStatusCode(response, 200);
    }
}
