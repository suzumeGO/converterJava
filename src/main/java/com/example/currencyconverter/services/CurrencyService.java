package com.example.currencyconverter.services;

import com.example.currencyconverter.CurrencyRepo;
import com.example.currencyconverter.DTO.ValCurs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepo repo;

    public ValCurs getData() {
        ValCurs valCurs = null;
        try {
            valCurs = repo.getData();
        } catch (IOException e) {
            //log
        }
        return valCurs;
    }

    public double exchange(String src, double amount, String dst) {
        double result;
        double secondValue = 0;
        ValCurs valCurs = getData();
        double firstValue = 0;

        if (src.equals("RUB")) {
            firstValue = 1;
        }
        if (dst.equals("RUB")) {
            secondValue = 1;
        }
        for (ValCurs.Valute valute : valCurs.getValutes()) {
            if (valute.getCharCode().equals(src)) {
                firstValue = Double.parseDouble(valute.getValue().replace(',', '.')) / valute.getNominal();
            }
            if (valute.getCharCode().equals(dst)) {
                secondValue = Double.parseDouble(valute.getValue().replace(',', '.')) / valute.getNominal();
            }
        }
        if (firstValue == 0 || secondValue == 0) {
            return 0;
        }
        result = firstValue * amount / secondValue;
        return Double.parseDouble(String.format("%.4f", result).replace(',', '.'));
    }
}
