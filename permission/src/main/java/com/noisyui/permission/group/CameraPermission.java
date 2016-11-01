package com.noisyui.permission.group;

import android.Manifest.permission;

import com.noisyui.permission.core.PermissionGroup;


public abstract class CameraPermission extends PermissionGroup {

    @Override
    public String[] getPermissions() {
        return new String[]{permission.CAMERA};
    }

}
