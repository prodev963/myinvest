package com.example.myinvest.controller;

import com.example.myinvest.config.CurrentUser;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.service.PackageService;
import com.example.myinvest.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/{username}")
@AllArgsConstructor
public class UserController {

    private final PackageService packageService;
    private final CurrentUser currentUser;
    private final StatisticsService statisticsService;


    @GetMapping("/stats")
    public ApiResponse getUserStats(@PathVariable String username) {
        return statisticsService.getUserStats(username);
    }

}
