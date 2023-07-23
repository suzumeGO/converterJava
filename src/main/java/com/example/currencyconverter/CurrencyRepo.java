package com.example.currencyconverter;


import com.example.currencyconverter.DTO.ValCurs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.client.fluent.Request;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrencyRepo {
    private static final String COURSES = "https://cbr.ru/scripts/XML_daily.asp";

    private String courses = getDataFromSource(COURSES);

    @Scheduled(fixedRate = 600000)
    @Async
    private void setValCurs(){
        //log update
        courses = getDataFromSource(COURSES);
    }

    private String getDataFromSource(String url) {
        String resp = "";
        try {
            resp = Request.Get(url).connectTimeout(1000)
                    .execute().returnContent().asString();
        } catch (IOException e) {
            //log
        }
        //log receive
        return resp;
    }

    public ValCurs getData() throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(courses, ValCurs.class);
    }


}
