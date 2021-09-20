package com.example.testa.pojo.modules;



import static com.example.testa.pojo.Utilities.fromKtoC;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Details implements Serializable {

    private int pressure;

    private int humidity;

    private float temp;

    @SerializedName("feels_like")
    private float feelsLike;

    @SerializedName("temp_min")
    private float minTemp;

    @SerializedName("temp_max")
    private float maxTemp;

    @SerializedName("sea_level")
    private int seaLevel;

    @SerializedName("grnd_level")
    private int grandLevel;

    @SerializedName("temp_kf")
    private float tempKF;

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getTemp() {
        return temp;
    }

    public String getTempTxt() {
        return fromKtoC(temp);
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public String getFeelsLikeTxt() {
        return fromKtoC(feelsLike);
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public String getMinTempTxt() {
        return fromKtoC(minTemp);
    }

    public String getMaxTempTxt() {
        return fromKtoC(maxTemp);
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public int getGrandLevel() {
        return grandLevel;
    }

    public float getTempKF() {
        return tempKF;
    }
}
