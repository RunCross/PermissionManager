package top.crossrun.util.permission.task;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

public class PermissionRequestTaskWithFragment extends PermissionRequestTask {
    Fragment fragment;

    public PermissionRequestTaskWithFragment(Fragment fragment) {
        this.fragment = fragment;
    }


    /**
     * @return
     */
    @Override
    public Context getContext() {
        return fragment.getActivity();
    }

    @Override
    public Activity getActivity() {
        return fragment.getActivity();
    }

    /**
     * 申请权限
     *
     * @param requestCode
     * @param pers
     * @return
     */
    @Override
    public PermissionRequestTaskWithFragment request(@NonNull int requestCode, @NonNull String... pers) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragment.requestPermissions(pers, requestCode);
        } else {
            ActivityCompat.requestPermissions(getActivity(), pers, requestCode);
        }
        return this;
    }
}
