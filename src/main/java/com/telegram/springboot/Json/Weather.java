package com.telegram.springboot.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.JsonNode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class Weather {

    public Main main;
    public Wind wind;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "Погода на сегодня \n" + main+"\n" + wind;
    }
}


