package com.noisyui.permission.core;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class PermissionProxyFragment extends Fragment implements PermissionProxy {

    private PermissionGroupContainer mContainer = new PermissionGroupContainer();

    @Override
    public void checkPermissions(PermissionGroup group) {
        group.checkPermissions(this);
    }

    @Override
    public void requestPermissions(PermissionGroup group) {
        mContainer.addPermissions(group);
        requestPermissions(group.getUnGranted(), group.getRequestCode());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mContainer.verifyPermissions(requestCode, grantResults);
    }
}