package com.restassured.testcases.Demo;

import com.restassured.common.BaseTest;
import com.restassured.globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllBook extends BaseTest {
    @Test
    public void getAllBooks(){
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        Response response = request.when().get("/books");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
