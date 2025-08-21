package com.restassured.model;
import lombok.*;

@Data //Bao gom Getter va Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginPOJO {
    private String username;
    private String password;
}
