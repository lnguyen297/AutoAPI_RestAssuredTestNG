package com.restassured.testcases.Category;

import com.google.gson.Gson;
import com.restassured.common.BaseTest;
import com.restassured.dataproviders.DataProviderFactory;
import com.restassured.globals.EndPointGlobal;
import com.restassured.keywords.ApiKeyword;
import com.restassured.model.Category_Model;
import com.restassured.testcases.Demo.CategoryTest_AllureReport;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class Category_Test extends BaseTest {
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Regression Test")
    @Feature("Category Management")
    @Story("Create Category")
    @Description("Verify that category can be created successfully with valid data")
    @Link("https://jira.com/anhtester/apitest/category/10")
    @Test(dataProvider = "createCategorySuccess", dataProviderClass = DataProviderFactory.class,priority = 1)
    public void testCreateCategorySucess(Hashtable<String, String> data) {
        Category_Model Category_Model = new Category_Model();
        Category_Model.setName(data.get("NAME"));

        //Chuyển POJO thành JSON String
        Gson gson = new Gson();
        String requestBody = gson.toJson(Category_Model);

        //Gọi API
        Response response = ApiKeyword.post(EndPointGlobal.EP_CATEGORY, requestBody);

        //Verify status code
        ApiKeyword.verifyStatusCode(response, 200);

        //Verify name
        ApiKeyword.verifyKeyValue(response, data.get("NAME"), "response.name");
        //ApiKeyword.verifyKeyValue( response, data.get("PHONE"), "response.phone");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Regression Test")
    @Feature("Category Management")
    @Story("Create Category")
    @Description("Verify that category can be created failed with valid data")
    @Link("https://jira.com/anhtester/apitest/category/10")
    @Test(dataProvider = "createCategoryFailure", dataProviderClass = DataProviderFactory.class,priority = 1)
    public void testCreateCategoryFailure(Hashtable<String, String> data){
        Category_Model Category_Model = new Category_Model();
        Category_Model.setName(data.get("NAME"));

        //Chuyển POJO thành JSON String
        Gson gson = new Gson();
        String requestBody = gson.toJson(Category_Model);

        //Gọi API
        Response response = ApiKeyword.post(EndPointGlobal.EP_CATEGORY, requestBody);

        //Verify status code
        ApiKeyword.verifyFailureStatusCode(response, 200);



    }

}
