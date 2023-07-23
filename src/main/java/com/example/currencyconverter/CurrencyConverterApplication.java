package com.example.currencyconverter;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Log4j2
@SpringBootApplication
public class CurrencyConverterApplication {

	public static final String APPLICATION_RUNS = "Application runs";

	public static void main(String[] args) {
		log.info(APPLICATION_RUNS);
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}



}
