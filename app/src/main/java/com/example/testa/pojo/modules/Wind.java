package com.example.testa.pojo.modules;

import java.io.Serializable;

public class Wind implements Serializable {

    private float speed;

    private int deg;

    private float gust;

    public float getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

    public float getGust() {
        return gust;
    }
}
