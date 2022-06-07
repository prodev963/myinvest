package com.example.myinvest.controller;

import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.service.StatisticsService;
import com.example.myinvest.service.UserService;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/menu")
@AllArgsConstructor
public class MenuController {

    private final UserService userService;
    private final StatisticsService statisticsService;

    @GetMapping("/statistics")
    public ApiResponse getStatisticsList() {
        return statisticsService.getStatisticsPage();
    }

    @GetMapping("/users")
    public ApiResponse searchForUsers(@SearchSpec Specification<UserEntity> specs) {
        return new ApiResponse("Get all", true, 22, userService.getUsers(specs));
    }

}