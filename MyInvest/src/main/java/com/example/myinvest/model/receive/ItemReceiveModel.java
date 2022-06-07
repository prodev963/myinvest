package com.example.myinvest.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ItemReceiveModel {

    private String name;
    //ex: olma, olcha ...
    private String description;
    //ex: card or cash
    @JsonProperty("payment_type")
    private String paymentType;
    //ex: 12000
    private BigDecimal price;
    //created date
    private LocalDate date;

}
