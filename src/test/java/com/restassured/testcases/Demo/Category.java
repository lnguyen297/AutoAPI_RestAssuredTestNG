package com.restassured.testcases.Demo;

import com.restassured.common.BaseTest;
import com.restassured.globals.TokenGlobal;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Category extends BaseTest {

    int CATEGORY_ID;

    @Test(priority = 1)
    public void createCategory(){
        System.out.println("Create Category:");
        String dataFile = "src/test/resources/testdata/category/CreateCategory.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(dataFile));

        Response respsonse = request.when().post("/category");
        respsonse.prettyPrint();
        respsonse.then().statusCode(200);

        CATEGORY_ID = Integer.parseInt(respsonse.path("response.id").toString());
        //System.out.println(CATEGORY_ID);


//        JsonPath jsonPath = respsonse.jsonPath();
//        CATEGORY_ID = Integer.parseInt(jsonPath.get("response.id").toString());
//        System.out.println(CATEGORY_ID);

    }

    @Test(priority = 2)
    public void getCategoryById(){
        System.out.println("Get Category By Id:");

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        Response respsonse = request.when().get("/category/" + CATEGORY_ID);
        respsonse.prettyPrint();
        respsonse.then().statusCode(200);

        JsonPath jsonPath = respsonse.jsonPath();
        Assert.assertEquals(jsonPath.get("response.name"), "Romance 09", "Name category not match.");

    }

    @Test(priority = 3)
    public void updateCategoryById(){
        System.out.println("Update Category:");
        String dataFile = "src/test/resources/testdata/category/UpdateCategory.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(dataFile));

        Response respsonse = request.when().put("/category/" + CATEGORY_ID);
        respsonse.prettyPrint();
        respsonse.then().statusCode(200);

        JsonPath jsonPath = respsonse.jsonPath();
        Assert.assertEquals(jsonPath.get("response.name"), "Romance 10", "Name category not match.");

    }
}
