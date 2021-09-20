package com.example.testa.pojo.modules;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {

    private int id;

    private String name;

    private String country;

    private int population;

    private Coord coord;

    @SerializedName("timezone")
    private long timeZone;

    @SerializedName("sunrise")
    private long sunRise;

    @SerializedName("sunset")
    private long sunSet;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCounty() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public long getTimeZone() {
        return timeZone;
    }

    public long getSunRise() {
        return sunRise;
    }

    public long getSunSet() {
        return sunSet;
    }

    public Coord getCoord() {
        return coord;
    }

}
