package top.crossrun.util.permission.task;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

public class PermissionRequestTaskWithContext extends PermissionRequestTask {
    Context context;

    public PermissionRequestTaskWithContext(Context context) {
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
    public void request(@NonNull int requestCode, @NonNull String... pers) {
        Activity activity = getActivity();
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(pers, requestCode);
            } else {
                ActivityCompat.requestPermissions(activity, pers, requestCode);
            }
        }
    }
}
