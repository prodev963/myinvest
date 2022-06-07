package com.example.myinvest.controller;

import com.example.myinvest.model.receive.PackageReceiveModel;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{username}")
public class PackageController {

    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PreAuthorize(value = "hasAuthority('USER')")
    @PostMapping("/add")
    public ApiResponse addPackage(
            @RequestBody PackageReceiveModel packageReceiveModel,
            @PathVariable String username){
        return packageService.addPackage(packageReceiveModel);
    }

    @PreAuthorize(value = "hasAuthority('USER')")
    @GetMapping("/")
    public ApiResponse getPackagesList(@PathVariable String username){
        return packageService.getPackageList(username);
    }



}
