package com.restassured.testcases.Demo;

import com.google.gson.Gson;
import com.restassured.common.BaseTest;
import com.restassured.globals.ConfigsGlobal;
import com.restassured.globals.TokenGlobal;
import com.restassured.helpers.JsonHelper;
import com.restassured.model.BookPOJO;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Book extends BaseTest {

    int CATEGORY_ID;

    @Test(priority = 1)
    public void createCategory(){

        System.out.println("Create Category:");
        String dataFile = "src/test/resources/testdata/category/CreateCategory.json";

        Faker faker = new Faker();
        JsonHelper.updateValueInJson(dataFile, "name", faker.book().genre());

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
    public void createBook(){
        System.out.println("Create Book:");

        Faker faker = new Faker(new Locale("vi"));

        BookPOJO bookPOJO = new BookPOJO();
        bookPOJO.setName(faker.book().title());
        bookPOJO.setCategory_id(CATEGORY_ID);
        bookPOJO.setPrice(120000);
        bookPOJO.setRelease_date("2015-12-03");
        bookPOJO.setStatus(true);

        //Cách 1:
//        ArrayList<Integer> imagesID = new ArrayList<>();
//        imagesID.add(12);
//        imagesID.add(61);
        //bookPOJO.setImage_ids(imagesID);

        //Cách 2:
        bookPOJO.setImage_ids(new ArrayList<>(Arrays.asList(12, 61)));

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.BASE_URI)
                .accept(ConfigsGlobal.ACCEPT)
                .contentType(ConfigsGlobal.CONTENT_TYPE)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(bookPOJO));

        Response response = request.when().post("/book");
        response.prettyPrint();
        response.then().statusCode(200);


    }
}
