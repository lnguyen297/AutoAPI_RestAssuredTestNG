package com.restassured.testcases.Demo;

import com.restassured.common.BaseTest;
import com.restassured.globals.EndPointGlobal;
import com.restassured.keywords.ApiKeyword;
import com.restassured.listeners.TestListener;
import com.restassured.utils.LogUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class CategoryTest_Keyword extends BaseTest {

    int CATEGORY_ID;
    String CATEGORY_NAME;

    @Test(priority = 1)
    public void testAddNewCategory() {
        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        Response response = ApiKeyword.post(EndPointGlobal.EP_CATEGORY, dataFile);

        response.then().statusCode(200);

        CATEGORY_ID = Integer.parseInt(ApiKeyword.getResponseKeyValue(response, "response.id"));
        CATEGORY_NAME = ApiKeyword.getResponseKeyValue(response, "response.name");

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        LogUtils.info("CATEGORY_NAME: " + CATEGORY_NAME);

    }

    @Test(priority = 2)
    public void getCategoryById() {

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        Response response = ApiKeyword.get("/category/" + CATEGORY_ID);

        response.then().statusCode(200);

        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "response.name"), CATEGORY_NAME, "The Category Name not match.");

    }

}