package com.noisyui.permission.group;

import android.Manifest.permission;

import com.noisyui.permission.core.PermissionGroup;


public abstract class WriteExternalStoragePermission extends PermissionGroup {

    @Override
    public String[] getPermissions() {
        return new String[]{permission.WRITE_EXTERNAL_STORAGE};
    }

}
