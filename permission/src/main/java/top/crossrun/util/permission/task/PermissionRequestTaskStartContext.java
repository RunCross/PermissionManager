package top.crossrun.util.permission.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import top.crossrun.util.permission.PermissionRequestActivity;

public class PermissionRequestTaskStartContext extends PermissionRequestTask {
    Context context;

    public PermissionRequestTaskStartContext(Context context) {
        this.context = context;
    }

    /**
     * @return
     */
    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Activity getActivity() {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    /**
     * 申请权限
     *
     * @param requestCode
     * @param pers
     * @return
     */
    @Override
    public PermissionRequestTaskStartContext request(@NonNull int requestCode, @NonNull String... pers) {
        Activity activity = getActivity();
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Intent intent = new Intent(activity, PermissionRequestActivity.class);
                intent.putExtra("requestCode", requestCode);
                intent.putExtra("permissions", pers);
                activity.startActivityForResult(intent, requestCode);
            } else {
                ActivityCompat.requestPermissions(activity, pers, requestCode);
            }
        }
        return this;
    }
}
