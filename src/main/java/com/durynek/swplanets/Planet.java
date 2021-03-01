package com.durynek.swplanets;

import javax.persistence.*;

@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(unique=true, nullable = false)
    private String name;
    private String climate;
    private String terrain;
    private Integer appearances = 0;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        // set appearances given name
        SwapiRestApi api = new SwapiRestApi();
        SwapiPlanet planet = api.getPlanetByName(name);
        if(planet != null)
            this.appearances = planet.getFilms().size();
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public Integer getAppearances() {
        return appearances;
    }
}
