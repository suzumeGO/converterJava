package com.example.currencyconverter;

import com.example.currencyconverter.DTO.ReceivedCurrencies;
import com.example.currencyconverter.DTO.SendCurrencies;
import com.example.currencyconverter.DTO.ValCurs;
import com.example.currencyconverter.services.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping(path = "/")

public class CurrencyController {
    public static final String GET_REQUEST_MESSAGE = "GET request";
    public static final String POST_REQUEST_MESSAGE = "POST request with id: ";
    @Autowired
    private CurrencyService service;

    @GetMapping
    public ValCurs getRates() {
        log.info(GET_REQUEST_MESSAGE);
        return service.getData();
    }

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public SendCurrencies getRate(@RequestBody ReceivedCurrencies receivedCurrencies) {
        SendCurrencies resp = new SendCurrencies(LocalDateTime.now().toString(),
                service.exchange(receivedCurrencies.getFirstCurrency(),
                        receivedCurrencies.getAmount(),
                        receivedCurrencies.getSecondCurrency()));
        log.info(POST_REQUEST_MESSAGE + resp.getReqId());
        return resp;
    }


}
