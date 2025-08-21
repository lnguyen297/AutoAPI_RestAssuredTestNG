package com.restassured.model;

import lombok.*;

@Data //Bao gom Getter va Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserPOJO_Lombok {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
