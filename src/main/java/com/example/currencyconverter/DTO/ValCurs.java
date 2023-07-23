package com.example.currencyconverter.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "ValCurs")
public class ValCurs {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    private List<ValCurs.Valute> valutes;

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    private String date;
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;

    @Data
    public static class Valute {
        @JacksonXmlProperty(localName = "ID", isAttribute = true)
        private String id;
        @JacksonXmlProperty(localName = "NumCode")
        private int numCode;
        @JacksonXmlProperty(localName = "CharCode")
        private String charCode;
        @JacksonXmlProperty(localName = "Nominal")
        private long nominal;
        @JacksonXmlProperty(localName = "Name")
        private String name;
        @JacksonXmlProperty(localName = "Value")
        private String value;
    }
}



