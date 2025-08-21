package com.restassured.data;

import com.restassured.globals.ConfigsGlobal;
import com.restassured.model.LoginPOJO;

public class LoginPoJO_Builder {
    public static LoginPOJO getDataLogin() {
        // Using Builder pattern to create an instance of LoginPOJO
        return LoginPOJO.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }
}