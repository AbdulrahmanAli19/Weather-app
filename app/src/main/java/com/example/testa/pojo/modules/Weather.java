package com.example.testa.pojo.modules;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Weather implements Serializable {

    private String main;

    @SerializedName("description")
    private String desc;

    private String icon;

    public String getMain() {
        return main;
    }

    public String getDesc() {
        return desc;
    }

    public String getIcon() {
        return icon;
    }
}
