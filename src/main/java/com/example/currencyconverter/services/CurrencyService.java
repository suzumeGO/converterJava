package com.example.currencyconverter.services;

import com.example.currencyconverter.CurrencyRepo;
import com.example.currencyconverter.DTO.ValCurs;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Log4j2
@Service
public class CurrencyService {
    public static final String DATA_FROM_REPO = "Get data from repo";
    public static final String EXCHANGING = "Starting exchanging";
    public static final String RUB_CHAR_CODE = "RUB";
    public static final String END_EXCHANGE_MSG = "Rates calculated";
    public static final int RUB_VALUE = 1;
    @Autowired
    private CurrencyRepo repo;

    public ValCurs getData() {
        ValCurs valCurs = null;
        try {
            valCurs = repo.getData();
            log.info(DATA_FROM_REPO);
        } catch (IOException e) {
            log.error(e);
        }
        return valCurs;
    }

    public double exchange(String src, double amount, String dst) {
        log.info(EXCHANGING);
        double result;
        double secondValue = 0;
        ValCurs valCurs = getData();
        double firstValue = 0;

        if (src.equals(RUB_CHAR_CODE)) {
            firstValue = RUB_VALUE;
        }
        if (dst.equals(RUB_CHAR_CODE)) {
            secondValue = RUB_VALUE;
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
        log.info(END_EXCHANGE_MSG);
        return Double.parseDouble(String.format("%.4f", result).replace(',', '.'));
    }
}
