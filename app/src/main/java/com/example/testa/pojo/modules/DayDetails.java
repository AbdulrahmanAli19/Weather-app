package com.example.testa.pojo.modules;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DayDetails implements Serializable {

    @SerializedName("dt")
    private Long date;

    @SerializedName("main")
    private Details details;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("dt_txt")
    private String dateTxt;


    public String getDateForDateTxt() {
        String myFormat = "EEEE, dd MMMM ";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return sdf.format(date * 1000);
    }

     public String getTime() {
        String myFormat = "hh:mm ";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return sdf.format(date * 1000);
    }


    public Long getDate() {
        return date;
    }

    public Details getDetails() {
        return details;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }

    public String getDateTxt() {
        return dateTxt;
    }
}
