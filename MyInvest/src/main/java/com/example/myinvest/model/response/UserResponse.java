package com.example.myinvest.model.response;

import com.example.myinvest.entity.PackageEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserResponse {

    private long id;
    @JsonProperty("full_name")
    private String fullName;
    private String username;
    private String password;
    private Date birthday;
    private String gender;
    @JsonProperty("packages_list")
    private List<PackageEntity> packagesList;

}
