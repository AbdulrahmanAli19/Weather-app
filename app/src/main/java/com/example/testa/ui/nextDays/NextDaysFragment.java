package com.example.testa.ui.nextDays;


import static com.example.testa.pojo.Utilities.fillNextDaysArray;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testa.databinding.NextDaysFragmentBinding;
import com.example.testa.pojo.modules.DayDetails;
import com.example.testa.pojo.modules.WeatherRespon;

import java.text.ParseException;
import java.util.ArrayList;

public class NextDaysFragment extends Fragment {

    private NavController controller;

    private WeatherRespon weatherRespon;
    private ArrayList<DayDetails> dayDetails = new ArrayList<>();
    private NextDaysFragmentBinding binding;

    public static NextDaysFragment newInstance() {
        return new NextDaysFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NextDaysFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        weatherRespon = NextDaysFragmentArgs.fromBundle(getArguments()).getDetails();
        binding.setD(weatherRespon);
        fillTheArray();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(new NextDaysAdapter(getContext(), dayDetails));
        return root;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
    }

    private void fillTheArray() {
        try {
            fillNextDaysArray(weatherRespon, dayDetails);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}