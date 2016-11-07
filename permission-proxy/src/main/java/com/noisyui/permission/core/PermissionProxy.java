package com.noisyui.permission.core;

import android.app.Activity;


/**
 * Created by jimsmac on 16/4/23.
 */
public interface PermissionProxy {

    Activity getActivity();

    void checkPermissions(PermissionGroup group);

    void requestPermissions(PermissionGroup group);

}
