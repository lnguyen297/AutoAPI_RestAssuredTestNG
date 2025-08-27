package com.restassured.testcases.Demo;

import com.restassured.common.BaseTest;
import com.restassured.globals.EndPointGlobal;
import com.restassured.helpers.JsonHelper;
import com.restassured.keywords.ApiKeyword;
import com.restassured.listeners.TestListener;
import com.restassured.utils.LogUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Locale;

@Listeners(TestListener.class)
public class CategoryTest_AllureReport extends BaseTest {

    int CATEGORY_ID;
    String CATEGORY_NAME;

    @Test(priority = 1)
    @Severity(SeverityLevel.MINOR)
    @Epic("Regression Test")
    @Feature("Category Test")
    @Story("Test Add New Category")
    @Description("Add new Category")
    @Link("https://jira.com/anhtester/apitest/books/10")
    public void testAddNewCategory() {
        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        Faker faker = new Faker(new Locale("vi"));
        CATEGORY_NAME = faker.job().title();
        System.out.println("CATEGORY_NAME: " + CATEGORY_NAME);

        JsonHelper.updateValueInJson(dataFile, "name", CATEGORY_NAME);

        Response response = ApiKeyword.post(EndPointGlobal.EP_CATEGORY, new File(dataFile));

        //response.then().statusCode(200);
        ApiKeyword.verifyStatusCode(response, 200);

        CATEGORY_ID = Integer.parseInt(ApiKeyword.getResponseKeyValue(response, "response.id"));
        CATEGORY_NAME = ApiKeyword.getResponseKeyValue(response, "response.name");

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        LogUtils.info("CATEGORY_NAME: " + CATEGORY_NAME);

    }

    @Test(priority = 2)
    @Severity(SeverityLevel.MINOR)
    @Epic("Regression Test")
    @Feature("Category Test")
    @Story("Test Get Category By ID")
    @Description("Get Category By ID")
    @Link("https://jira.com/anhtester/apitest/books/10")
    public void getCategoryById() {

//        RequestSpecification request = given();
//        request.baseUri("https://api.anhtester.com/api")
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        Response response = ApiKeyword.get(EndPointGlobal.EP_CATEGORY + "/" + CATEGORY_ID);

        response.then().statusCode(200);

        Assert.assertEquals(ApiKeyword.getResponseKeyValue(response, "response.name"), CATEGORY_NAME, "The Category Name not match.");

    }
}
