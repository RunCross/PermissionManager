package top.crossrun.util.permission.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

public class PermissionRequestTaskStartAcitivity extends PermissionRequestTask {
    Activity activity;

    public PermissionRequestTaskStartAcitivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return
     */
    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    /**
     * 申请权限
     *
     * @param requestCode
     * @param pers
     * @return onActivityResult <br>
     * intent <br>
     * data.getStringArrayListExtra("allow") <br>
     * data.getStringArrayListExtra("disallow") <br>
     */
    @Override
    public <T extends PermissionRequestTask> PermissionRequestTask request(@NonNull int requestCode, @NonNull String... pers) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(activity, PermissionRequestTaskStartAcitivity.class);
            intent.putExtra("requestCode", requestCode);
            intent.putExtra("permissions", pers);
            activity.startActivityForResult(intent, requestCode);
        } else {
            ActivityCompat.requestPermissions(activity, pers, requestCode);
        }
        return this;
    }
}
