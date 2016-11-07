package com.noisyui.permission.core;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * isAllGranted -- shouldShowRationale -- showRationale -- requestPermissions -- verify
 */
public class PermissionGroup implements PermissionGrantCallback {

    private static int sBaseCode = 0;

    /**
     * 在fragment中只能使用低八位
     */
    private int mRequestCode;

    private PermissionProxy mPermissionProxy;

    private String[] mPermissions;

    private String[] mUnGranted;

    private PermissionRationale mRationale;

    public PermissionGroup(){
        refreshRequestCode();
    }

    public PermissionGroup(String... permissions) {
        mPermissions = permissions;
        refreshRequestCode();
    }

    /**
     * make sure requestCode in [0, 255]
     */
    private void refreshRequestCode() {
        if (sBaseCode > 0xff) {
            sBaseCode = 0;
        }

        mRequestCode = sBaseCode++;
    }

    void checkPermissions(PermissionProxy proxy) {
        mPermissionProxy = proxy;
        Activity activity = proxy.getActivity();
        if (isAllGranted(activity)) {
            onChecked();
        } else if (shouldShowRationale(activity)) {
            showRationale(activity);
        } else {
            requestPermissions();
        }
    }

    public boolean isAllGranted(Context context) {
        mUnGranted = getUnGranted(context, getPermissions());
        return mUnGranted.length == 0;
    }

    private String[] getUnGranted(Context context, String[] permissions) {
        List<String> unGranted = new ArrayList<>();
        if (null != permissions && permissions.length > 0) {
            for (String permission : permissions) {
                if (!checkSelfPermission(context, permission)) {
                    unGranted.add(permission);
                }
            }
        }
        return unGranted.toArray(new String[unGranted.size()]);
    }

    private boolean checkSelfPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    protected String[] getPermissions() {
        return mPermissions;
    }

    private void onChecked() {
        onGranted();
    }

    private boolean shouldShowRationale(Activity activity) {
        for (String permission : mUnGranted) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    private void showRationale(Activity activity) {
        if (null == mRationale) {
            requestPermissions();
        } else {
            mRationale.showRationale(activity);
        }
    }

    public void requestPermissions() {
        mPermissionProxy.requestPermissions(this);
    }

    public PermissionGroup setupRationale(PermissionRationale rationale) {
        mRationale = rationale;
        mRationale.setPermissionGroup(this);
        return this;
    }

    String[] getUnGranted() {
        return mUnGranted;
    }

    int getRequestCode() {
        return mRequestCode;
    }

    /**
     * Check that all given permissions have been granted by verifying that each entry in the
     * given array is of the value {@link PackageManager#PERMISSION_GRANTED}.
     *
     * @see Activity#onRequestPermissionsResult(int, String[], int[])
     */
    static boolean verify(int[] grantResults) {
        // At least one result must be checked.
        if (grantResults.length < 1) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onGranted() {

    }

    @Override
    public void onDenied() {

    }
}
