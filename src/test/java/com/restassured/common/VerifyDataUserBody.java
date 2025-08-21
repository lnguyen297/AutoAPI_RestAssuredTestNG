package com.restassured.common;

import com.restassured.model.RegisterUserPOJO_Lombok;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerifyDataUserBody {
    public static void verifyDataUserBody(Response response, RegisterUserPOJO_Lombok registerUserPOJO_lombok){
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("response.username"), registerUserPOJO_lombok.getUsername(), "Username not match");
        Assert.assertEquals(jsonPath.get("response.firstName"), registerUserPOJO_lombok.getFirstName(), "First name not match");
        Assert.assertEquals(jsonPath.get("response.lastName"), registerUserPOJO_lombok.getLastName(), "Last name not match");
        Assert.assertEquals(jsonPath.get("response.email"), registerUserPOJO_lombok.getEmail(), "Email not match");
        Assert.assertEquals(jsonPath.get("response.phone"), registerUserPOJO_lombok.getPhone(), "Phone not match");
        Assert.assertEquals(Integer.parseInt(jsonPath.get("response.userStatus").toString()), registerUserPOJO_lombok.getUserStatus(), "User status not match");
        Assert.assertEquals(jsonPath.get("response.id").toString(), 1986, "ID not match");
        Assert.assertEquals(jsonPath.get("message"), "Success", "Message not match");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code not match");
    }
}
