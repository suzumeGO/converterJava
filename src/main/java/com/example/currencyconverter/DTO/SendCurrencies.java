package com.example.currencyconverter.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SendCurrencies {
    public SendCurrencies(String reqDate, double amount) {
        this.reqId = UUID.randomUUID().toString();
        this.reqDate = reqDate;
        this.amount = amount;
    }
    private String reqId;
    private String reqDate;
    private double amount;

}
