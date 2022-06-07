package com.example.myinvest.model.receive;


import com.example.myinvest.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RegistrationReceiveModel {

    @JsonProperty("full_name")
    @NotBlank(message = "full name should not be empty ")
    private String fullName;

    @JsonProperty("username")
    @NotEmpty(message = "username should not be empty")
    private String username;

    @NotEmpty(message = "password should not be empty")
    private String password;

    @NotEmpty(message = "birthday should not be empty")
    @JsonProperty("birthday")
    private LocalDate birthday;

    @NotEmpty(message = "gender should not be empty")
    private String gender;

    @JsonProperty("user_role")
    private UserRole roleEnum;
}
