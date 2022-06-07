package com.example.myinvest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //ex: bazarlik
    private String name;
    //ex: olma, olcha ...
    private String description;
    //ex: card or cash
    private String paymentType;
    //ex: 12000
    private BigDecimal price;
    //created date
    private LocalDate date;

    public ItemEntity(String name, String description, String paymentType, BigDecimal price, LocalDate date) {
        this.name = name;
        this.description = description;
        this.paymentType = paymentType;
        this.price = price;
        this.date = date;
    }
}
