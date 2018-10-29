package top.crossrun.util.permission.task;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import top.crossrun.util.permission.PermissionRequestActivity;

public class PermissionRequestTaskStartFragment extends PermissionRequestTask {
    Fragment fragment;

    public PermissionRequestTaskStartFragment(Fragment fragment) {
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
    public void request(@NonNull int requestCode, @NonNull String... pers) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(getContext(), PermissionRequestActivity.class);
            intent.putExtra("requestCode", requestCode);
            intent.putExtra("permissions", pers);
            fragment.startActivityForResult(intent, requestCode);
        } else {
            ActivityCompat.requestPermissions(getActivity(), pers, requestCode);
        }
    }
}
