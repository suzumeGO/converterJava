package com.example.currencyconverter;


import com.example.currencyconverter.DTO.ValCurs;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.fluent.Request;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Log4j2
@Repository
public class CurrencyRepo {
    private static final String COURSES = "https://cbr.ru/scripts/XML_daily.asp";
    public static final String DATA_FROM = "XML with rates are obtained from: ";
    public static final String ERROR_MSG = "Cannot obtain the data";
    public static final String UNMARSHALING_MSG = "Convert to java object";
    public static final String UPDATE_RATES_MSG = "Update rates";
    public static final String EMPTY_STRING = "";
    public static final int TIMEOUT = 1000;
    private String courses = getDataFromSource(COURSES);

    @Scheduled(fixedRate = 600000)
    @Async
    private void setValCurs(){
        courses = getDataFromSource(COURSES);
        log.info(UPDATE_RATES_MSG);
    }

    private String getDataFromSource(String url) {
        String resp = EMPTY_STRING;
        try {
            resp = Request.Get(url).connectTimeout(TIMEOUT)
                    .execute().returnContent().asString();
            log.info(DATA_FROM + url);
        } catch (IOException e) {
            log.error(ERROR_MSG, e);
        }
        return resp;
    }

    public ValCurs getData() throws IOException {
        XmlMapper mapper = new XmlMapper();
        log.info(UNMARSHALING_MSG);
        return mapper.readValue(courses, ValCurs.class);
    }
}
