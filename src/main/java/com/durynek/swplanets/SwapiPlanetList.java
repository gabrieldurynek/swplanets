package com.durynek.swplanets;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SwapiPlanetList {
    @JsonProperty private Integer count;
    @JsonProperty private String next;
    @JsonProperty private String previous;
    @JsonProperty private List<SwapiPlanet> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<SwapiPlanet> getResults() {
        return results;
    }
}
