package top.crossrun.util.permission.task;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

public class PermissionRequestTaskWithAcitivity extends PermissionRequestTask<PermissionRequestTaskWithAcitivity> {
    Activity activity;

    public PermissionRequestTaskWithAcitivity(Activity activity) {
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
     * @return
     */
    @Override
    public PermissionRequestTaskWithAcitivity request(@NonNull int requestCode, @NonNull String... pers) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(pers, requestCode);
        } else {
            ActivityCompat.requestPermissions(activity, pers, requestCode);
        }
        return this;
    }
}
