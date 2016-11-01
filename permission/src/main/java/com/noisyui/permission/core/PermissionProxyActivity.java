package com.noisyui.permission.core;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class PermissionProxyActivity extends AppCompatActivity implements PermissionProxy {

    private PermissionGroupContainer mContainer = new PermissionGroupContainer();

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void checkPermissions(PermissionGroup group) {
        group.checkPermissions(this);
    }

    @Override
    public void requestPermissions(PermissionGroup group) {
        mContainer.addPermissions(group);
        ActivityCompat.requestPermissions(getActivity(), group.getUnGranted(), group.getRequestCode());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mContainer.verifyPermissions(requestCode, grantResults);
    }
}