package com.example.testa.ui.home;

import androidx.lifecycle.ViewModel;

import com.example.testa.repo.OpenWeatherRepo;


public class HomeViewModel extends ViewModel {


    public void getDataFromAPI(String lat, String lon, OnDataReceived onDataReceived) {
        OpenWeatherRepo openWeatherRepo = OpenWeatherRepo.getInstance();
        openWeatherRepo.getData(lat, lon, onDataReceived);
    }


}