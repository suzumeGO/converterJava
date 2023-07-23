package com.example.currencyconverter;

import com.example.currencyconverter.services.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurrencyConverterApplicationTests {
	@Autowired
	private CurrencyService service;
	@Test
	void contextLoads() {
	}

	@Test
	void exchangeTest() {
		Assertions.assertEquals(668.186, service.exchange("EUR",600,"USD"));
		Assertions.assertEquals(231888.1753, service.exchange("USD",600,"AMD"));
		Assertions.assertEquals(1.1488, service.exchange("AMD",1,"KZT"));
		Assertions.assertEquals(5.5319, service.exchange("RUB",500,"USD"));
	}

}
