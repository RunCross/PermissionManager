package top.crossrun.util.permission;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import top.crossrun.util.permission.task.PermissionRequestTask;
import top.crossrun.util.permission.task.PermissionRequestTaskStartAcitivity;
import top.crossrun.util.permission.task.PermissionRequestTaskStartContext;
import top.crossrun.util.permission.task.PermissionRequestTaskStartFragment;
import top.crossrun.util.permission.task.PermissionRequestTaskStartFragmentV4;
import top.crossrun.util.permission.task.PermissionRequestTaskWithAcitivity;
import top.crossrun.util.permission.task.PermissionRequestTaskWithContext;
import top.crossrun.util.permission.task.PermissionRequestTaskWithFragment;
import top.crossrun.util.permission.task.PermissionRequestTaskWithFragmentV4;

public class PermissionManager {

    public static PermissionRequestTask with(Activity activity) {
        return new PermissionRequestTaskWithAcitivity(activity);
    }

    public static PermissionRequestTask with(Fragment fragment) {
        return new PermissionRequestTaskWithFragmentV4(fragment);
    }

    public static PermissionRequestTask with(android.app.Fragment fragment) {
        return new PermissionRequestTaskWithFragment(fragment);
    }

    public static PermissionRequestTask with(Context context) {
        return new PermissionRequestTaskWithContext(context);
    }

    public static PermissionRequestTask start(Activity activity) {
        return new PermissionRequestTaskStartAcitivity(activity);
    }

    public static PermissionRequestTask start(Fragment fragment) {
        return new PermissionRequestTaskStartFragmentV4(fragment);
    }

    public static PermissionRequestTask start(android.app.Fragment fragment) {
        return new PermissionRequestTaskStartFragment(fragment);
    }

    public static PermissionRequestTask start(Context context) {
        return new PermissionRequestTaskStartContext(context);
    }
}
