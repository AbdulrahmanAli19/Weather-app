package com.example.testa.pojo.permissionHelper;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;

public class FragmentPermissionHelper {
    public void getUserPermission(FragmentActivity fragmentActivity,
                                 FragmentPermissionInterface permissionInterface,
                                  String permission) {
        ActivityResultLauncher<String> requestPermissionLauncher =
                fragmentActivity.registerForActivityResult(new ActivityResultContracts.RequestPermission(), permissionInterface::onGranted);
            requestPermissionLauncher.launch(permission);
    }
}
