package com.example.testa.ui.home;

import static android.content.pm.PackageManager.PERMISSION_DENIED;
import static com.example.testa.pojo.Utilities.fillCurrentDayArray;
import static com.example.testa.pojo.Utilities.setIcon;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testa.databinding.HomeFragmentBinding;
import com.example.testa.pojo.modules.DayDetails;
import com.example.testa.pojo.modules.WeatherRespon;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnDataReceived {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;
    private NavController navController;
    private WeatherRespon weatherRespon;
    private FragmentActivity fragmentActivity;

    private final String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        if (isPermissionsGranted())
            getWeatherData();
        else
            do {
                Log.d(TAG, "onCreateView: ");
                isPermissionsGranted();
            } while (!isPermissionsGranted());

        if (isPermissionsGranted())
            getWeatherData();

        binding.txtNext.setOnClickListener(v -> navController.navigate(HomeFragmentDirections
                .actionNavigationHomeToNavigationNotifications(weatherRespon)));

        return root;
    }


    private boolean isPermissionsGranted() {
        boolean isGranted = true;

        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(getActivity(), permission) == PERMISSION_DENIED)
                getPermissions(permission);

        }

        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(getActivity(), permission) == PERMISSION_DENIED) {
                isGranted = false;
                break;
            }
        }

        return isGranted;
    }

    private void getPermissions(String permission) {
        new FragmentPermissionHelper().getUserPermission(fragmentActivity, isGranted -> {
            if (isGranted) {
                Toast.makeText(getContext(), permission + " Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), permission + " Denied", Toast.LENGTH_SHORT).show();
            }
        }, permission);

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            fragmentActivity = (FragmentActivity) context;
        }
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view,
                              @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }


    @SuppressLint("MissingPermission")
    public void getWeatherData() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        OnDataReceived onDataReceived = this;

        locationManager.requestLocationUpdates("gps", 4000, 0f, location -> {
            String lat = String.valueOf(location.getLatitude());
            String lon = String.valueOf(location.getLongitude());
            mViewModel.getDataFromAPI(lat, lon, onDataReceived);
        });


    }

    private static final String TAG = HomeFragment.class.toString();

    @Override
    public void onResponse(WeatherRespon weatherRespon) {
        this.weatherRespon = weatherRespon;
        binding.progressBar.setVisibility(View.GONE);
        binding.mainScreen.setVisibility(View.VISIBLE);
        binding.setResponse(weatherRespon);

        binding.imgMain.setImageResource(setIcon(weatherRespon.getDayDetails()
                .get(0).getWeather().get(0).getIcon()));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<DayDetails> dayDetails = new ArrayList<>();
        fillCurrentDayArray(weatherRespon, dayDetails);
        Log.d(TAG, "onResponse: called.");
        binding.recyclerView.setAdapter(new CurrentDayAdapter(getContext(), dayDetails));
    }

}