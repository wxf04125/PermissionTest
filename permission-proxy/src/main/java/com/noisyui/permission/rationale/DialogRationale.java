package com.noisyui.permission.rationale;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.noisyui.permission.core.PermissionRationale;


/**
 * Created by jimsmac on 16/4/17.
 */
public class DialogRationale extends PermissionRationale {

    private int mStringId;

    public DialogRationale(int stringId) {
        mStringId = stringId;
    }

    @Override
    public void showRationale(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(mStringId);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPermissionGroup.requestPermissions();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();
    }
}
