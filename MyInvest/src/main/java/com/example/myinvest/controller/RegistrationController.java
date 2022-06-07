package com.example.myinvest.controller;

import com.example.myinvest.model.receive.LoginReceiveModel;
import com.example.myinvest.model.receive.RegistrationReceiveModel;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public HttpEntity<?> addUser(@Valid @RequestBody RegistrationReceiveModel registrationReceiveModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(registrationReceiveModel));
    }

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginReceiveModel userSignInReceiveModel){
        return userService.login(userSignInReceiveModel);
    }


}
