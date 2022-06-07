package com.example.myinvest.model.response;

import com.example.myinvest.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PackageResponse {

    private String name;
    private String type;

}
