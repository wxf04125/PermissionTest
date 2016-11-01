package com.noisyui.permission.core;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimsmac on 16/4/23.
 */
class PermissionGroupContainer {

    private List<PermissionGroup> mPermissionGroups = new ArrayList<>();

    void addPermissions(PermissionGroup group) {
        if (!mPermissionGroups.contains(group)) {
            mPermissionGroups.add(group);
        }
    }

    void verifyPermissions(int requestCode, @NonNull int[] grantResults) {
        if (null != mPermissionGroups && mPermissionGroups.size() > 0) {
            for (PermissionGroup permissionGroup : mPermissionGroups) {
                if (permissionGroup.getRequestCode() == requestCode) {
                    if (PermissionGroup.verify(grantResults)) {
                        permissionGroup.onGranted();
                    } else {
                        permissionGroup.onDenied();
                    }
                    mPermissionGroups.remove(permissionGroup);
                }
            }
        }
    }
}
