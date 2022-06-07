package com.example.myinvest.controller;

import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.repository.UserRepository;
import com.example.myinvest.service.StatisticsService;
import com.example.myinvest.service.UserService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final UserService userService;
    private final UserRepository userRepository;


    @Autowired
    public StatisticsController(StatisticsService statisticsService, UserService userService, UserRepository userRepository) {
        this.statisticsService = statisticsService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{age}/{gender}/{year}")
    public ApiResponse getStats(@PathVariable String age,
                                @PathVariable String gender,
                                @PathVariable String year) {
        return statisticsService.getStatistics(getInt(age), gender,getInt(year));
    }

    private int getInt(String s){
        try{
            return Integer.parseInt(s);
           }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return 0;
    }


}
