package com.example.myinvest.service;

import com.example.myinvest.model.response.ApiResponse;

public interface BaseService {

    ApiResponse SUCCESS = new ApiResponse("Siz muvaffaqiyatli ro'yxatdan o'tdingiz!", true, 0);

    ApiResponse LOGIN_SUC = new ApiResponse("logged in!", true, 0);


    ApiResponse USER_EXIST = new ApiResponse("Afsuski bunday username allaqachon mavjud", false, -100);

    ApiResponse PACKAGE_ADD_SUC = new ApiResponse("package muvafaqiyatli qoshildi", true, 0);
    ApiResponse PACKAGE_LIST = new ApiResponse("package List", true, 0);

    ApiResponse WRONG_PASSWORD = new ApiResponse("wrong password", false, -100);
    ApiResponse USER_NOT_FOUND = new ApiResponse("bu user topilmadi", false, -101);
    ApiResponse PACKAGE_EXIST = new ApiResponse("BUNDAY PACKAGE MAVJUD", true, -101);
    ApiResponse PACKAGE_NOT_FOUND = new ApiResponse("BUNDAY PACKAGE MAVJUD emas", false, -101);

    ApiResponse ITEM_ADD_SUC=new ApiResponse("Item muvaffaqiyatli qoshildi",true,100);
    ApiResponse ITEM_LIST=new ApiResponse("Item List",true,100);

    ApiResponse ALL_STATS=new ApiResponse("Barcha statistikalar",true,100);
    ApiResponse USER_STAT=new ApiResponse("Item muvaffaqiyatli qoshildi",true,100);

}
