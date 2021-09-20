package com.example.testa.repo;

import static com.example.testa.pojo.Utilities.startConnection;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.testa.pojo.data.network.OpenWeatherApi;
import com.example.testa.pojo.modules.WeatherRespon;
import com.example.testa.ui.home.OnDataReceived;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OpenWeatherRepo {

    private static final String TAG = "OpenWeatherRepo";

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private final String API_KEY = "5ef5f8bf184ca5e7d86a59392ca23a7c";

    private Retrofit retrofit;

    public static OpenWeatherRepo getInstance() {
        return new OpenWeatherRepo();
    }


    public void getData(String lat, String lon, OnDataReceived onDataReceived) {
        retrofit = startConnection(retrofit, BASE_URL);

        OpenWeatherApi openWeatherApi = retrofit.create(OpenWeatherApi.class);

        Call<WeatherRespon> call = openWeatherApi.getWeather(lat, lon, API_KEY);

        call.enqueue(new Callback<WeatherRespon>() {
            @Override
            public void onResponse(@NonNull Call<WeatherRespon> call, @NonNull Response<WeatherRespon> response) {
                onDataReceived.onResponse(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<WeatherRespon> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }
}
