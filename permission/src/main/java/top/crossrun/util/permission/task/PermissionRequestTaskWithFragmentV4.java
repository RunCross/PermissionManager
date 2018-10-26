package top.crossrun.util.permission.task;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

public class PermissionRequestTaskWithFragmentV4 extends PermissionRequestTask {
    Fragment fragment;

    public PermissionRequestTaskWithFragmentV4(Fragment fragment) {
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
    public <T extends PermissionRequestTask> PermissionRequestTask request(@NonNull int requestCode, @NonNull String... pers) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragment.requestPermissions(pers, requestCode);
        } else {
            ActivityCompat.requestPermissions(getActivity(), pers, requestCode);
        }
        return this;
    }
}
