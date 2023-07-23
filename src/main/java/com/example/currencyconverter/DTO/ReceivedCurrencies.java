package com.example.currencyconverter.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedCurrencies {

    private String firstCurrency;
    private double amount;
    private String secondCurrency;


}
