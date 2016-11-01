package com.noisyui.permission.group;

import android.Manifest.permission;

import com.noisyui.permission.core.PermissionGroup;


public class LocationGroup extends PermissionGroup {

    @Override
    public String[] getPermissions() {
        return new String[]{permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION};
    }

}
