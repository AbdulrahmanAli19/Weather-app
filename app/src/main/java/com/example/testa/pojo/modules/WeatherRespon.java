package com.example.testa.pojo.modules;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherRespon implements Serializable {

    private City city;

    @SerializedName("list")
    private ArrayList<DayDetails> dayDetails;

    private String cod;

    public City getCity() {
        return city;
    }

    public ArrayList<DayDetails> getDayDetails() {
        return dayDetails;
    }

    public String getCod() {
        return cod;
    }
}
