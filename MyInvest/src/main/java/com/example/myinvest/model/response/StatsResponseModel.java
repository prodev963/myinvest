package com.example.myinvest.model.response;

import lombok.Data;


import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;
import java.util.Map;


@Data
public class StatsResponseModel {

//    private String packageName;
//    private String packageType;
//    private int itemsNum;

//    private BigDecimal allMoney;
//    private BigDecimal allWithCard;
//    private BigDecimal allWithCash;
//
//    private BigDecimal thisMonthMoney;
//    private BigDecimal thisMonthCard;
//    private BigDecimal thisMonthCash;
//
//    private BigDecimal thisWeekMoney;
//    private BigDecimal thisWeekCard;
//    private BigDecimal thisWeekCash;
//
//    private BigDecimal thisDayMoney;
//    private BigDecimal thisDayCard;
//    private BigDecimal thisDayCash;
//
//    public StatsResponseModel(String packageName, String packageType, int itemsNum, BigDecimal allMoney, BigDecimal allWithCard, BigDecimal allWithCash, BigDecimal thisMonthMoney, BigDecimal thisMonthCard, BigDecimal thisMonthCash, BigDecimal thisWeekMoney, BigDecimal thisWeekCard, BigDecimal thisWeekCash, BigDecimal thisDayMoney, BigDecimal thisDayCard, BigDecimal thisDayCash) {
//        this.packageName = packageName;
//        this.packageType = packageType;
//        this.itemsNum = itemsNum;
//        this.allMoney = allMoney;
//        this.allWithCard = allWithCard;
//        this.allWithCash = allWithCash;
//        this.thisMonthMoney = thisMonthMoney;
//        this.thisMonthCard = thisMonthCard;
//        this.thisMonthCash = thisMonthCash;
//        this.thisWeekMoney = thisWeekMoney;
//        this.thisWeekCard = thisWeekCard;
//        this.thisWeekCash = thisWeekCash;
//        this.thisDayMoney = thisDayMoney;
//        this.thisDayCard = thisDayCard;
//        this.thisDayCash = thisDayCash;
//


    private Map<Month, List<BigDecimal>> monthlyStats;


}
