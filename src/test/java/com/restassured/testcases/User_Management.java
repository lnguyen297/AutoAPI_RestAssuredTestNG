package com.restassured.testcases;

import com.google.gson.Gson;
import com.restassured.common.BaseTest;
import com.restassured.constants.ConfigData;
import com.restassured.globals.EndPointGlobal;
import com.restassured.keywords.ApiKeyword;
import com.restassured.model.User_Model;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.util.Locale;

public class User_Management extends BaseTest {

    Faker faker = new Faker(new Locale("vi"));
    @Test
    public void testUpdateUser(){
        User_Model user = new User_Model();
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword("Demo@12345");
        user.setPhone("0657658364");
        user.setUserStatus(1);

        // Chuyển POJO thành JSON String
        Gson gson = new Gson();
        String requestBody = gson.toJson(user);

        // Gọi API
        Response response = ApiKeyword.put(EndPointGlobal.EP_USER + "/58", requestBody);

        ApiKeyword.verifyStatusCode(response, 200);
    }
}
