package com.telegram.springboot.Json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.JsonNode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class Weather {

//    public Coord coord;
//    public List<Weath> weathers;
//    public String base;
    public Main main;
//    public Wind wind;
//    public Clouds clouds;
//    public long dt;
//    public Sys sys;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "main=" + main +
                '}';
    }
}


