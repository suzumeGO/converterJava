package com.example.currencyconverter;

import com.example.currencyconverter.DTO.ReceivedCurrencies;
import com.example.currencyconverter.DTO.SendCurrencies;
import com.example.currencyconverter.DTO.ValCurs;
import com.example.currencyconverter.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/")

public class CurrencyController {
    @Autowired
    private CurrencyService service;

    @GetMapping
    public ValCurs getRates() {
        return service.getData();
    }

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public SendCurrencies getRate(@RequestBody ReceivedCurrencies receivedCurrencies) {
        return new SendCurrencies(LocalDateTime.now().toString(),
                service.exchange(receivedCurrencies.getFirstCurrency(),
                        receivedCurrencies.getAmount(),
                        receivedCurrencies.getSecondCurrency()));
    }


}
