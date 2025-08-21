package com.restassured.testcases;

import com.google.gson.Gson;
import com.restassured.common.BaseTest;
import com.restassured.common.VerifyDataUserBody;
import com.restassured.data.UserPOJO_Lombok_Builder;
import com.restassured.globals.ConfigsGlobal;
import com.restassured.globals.TokenGlobal;
import com.restassured.model.RegisterUserPOJO_Lombok;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoBaseTest extends BaseTest {
    @Test
    public void testUpdateUser_PATCH() {


        RegisterUserPOJO_Lombok registerUserPOJO_lombok = UserPOJO_Lombok_Builder.getUserData();


        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT)
                .contentType(ConfigsGlobal.CONTENT_TYPE)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJO_lombok));

        Response response = request.when().patch("/user/1986");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");

        VerifyDataUserBody.verifyDataUserBody(response, registerUserPOJO_lombok);
    }

}
