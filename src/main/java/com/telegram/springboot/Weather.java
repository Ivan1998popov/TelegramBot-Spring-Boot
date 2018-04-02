package com.telegram.springboot;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonProperty("coord")
    private JsonNode coord;

    public JsonNode  getCoord() {
        return coord;
    }

    public void setCoord(JsonNode coord) {
        this.coord = coord;
    }
}
