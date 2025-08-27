package com.restassured.testcases.Demo;

import com.restassured.common.BaseTest;
import com.restassured.globals.ConfigsGlobal;
//import com.restassured.model.data.UserPOJO_Lombok_Builder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class JsonSchemaValidationTest extends BaseTest {
    @Test
    public void validateJsonSchema_GetBookById() {
        InputStream GetBookIdSchema = getClass().getClassLoader()
                .getResourceAsStream("schema/GetBookIdSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.BASE_URI)
                .when()
                .get("/book/372")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetBookIdSchema));
    }

    @Test
    public void validateJsonSchema_GetBookAll() {
        InputStream GetAllBookSchema = getClass().getClassLoader()
                .getResourceAsStream("schema/GetAllBooksSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.BASE_URI)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetAllBookSchema));
    }


}