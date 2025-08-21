package com.restassured.data;

import com.restassured.model.RegisterUserPOJO_Lombok;
import net.datafaker.Faker;

import java.util.Locale;

public class UserPOJO_Lombok_Builder {
    public static RegisterUserPOJO_Lombok getUserData() {

        Faker faker = new Faker(new Locale("vi"));
        String phoneNumber = faker.numerify("09########");
        //phoneNumber = phoneNumber.replace(" ", "");

        return RegisterUserPOJO_Lombok.builder()
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phone(phoneNumber)
                .email(faker.internet().emailAddress())
                .userStatus(1)
                .build();
    }
}
