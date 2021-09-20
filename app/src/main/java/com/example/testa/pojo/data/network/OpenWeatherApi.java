package com.example.testa.pojo.data.network;


import com.example.testa.pojo.modules.WeatherRespon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("forecast")
    Call<WeatherRespon> getWeather(@Query("lat") String lat,
                                   @Query("lon") String lon,
                                   @Query("appid") String apiKey);
}
