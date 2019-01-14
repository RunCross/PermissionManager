package top.crossrun.util.permission.task;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.ArrayList;

import top.crossrun.util.permission.dialog.PermissionDialog;

public abstract class PermissionRequestTask<T extends PermissionRequestTask> {
    /**
     * 检查权限
     *
     * @param pers 权限
     * @return true = 全都被许可 ; false  = 至少有意向不被许可
     */
    public boolean check(@NonNull String... pers) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        Context context = getContext();
        for (String per :
                pers) {
            if (ContextCompat.checkSelfPermission(context, per) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }

        }
        return true;
    }


    /**
     * 检查权限
     *
     * @param pers 权限
     * @return 不被许可的权限, 返回一个长度为0的数组代表全部被许可
     */
    public String[] checks(@NonNull String... pers) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return new String[0];
        }
        Context context = getContext();
        ArrayList<String> result = new ArrayList<>();
        for (String per :
                pers) {
            if (ContextCompat.checkSelfPermission(context, per) != PackageManager.PERMISSION_GRANTED) {
                result.add(per);
            }
        }
        String[] r = new String[result.size()];
        return result.toArray(r);
    }

    public void autoCheck(@NonNull int requestCode, @NonNull String... pers){
        String[] p = checks(pers);
        if (p.length>0){
            request(requestCode, pers);
        }
    }

    /**
     * 拒绝后的再次申请,弹出说明框
     *
     * @param title           标题
     * @param msg             申请理由
     * @param onClickListener 取消按钮的回调
     * @return true = 弹出申请框,false= 无需申请或者传入参数不对
     */
    public boolean requestRationale(@NonNull String title, @NonNull String msg, DialogInterface.OnClickListener onClickListener, @NonNull int requestCode, @NonNull String... pers) {
        Activity act = getActivity();
        if (act == null) {
            return false;
        }
//        for (String per :
//                pers) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(act, per)) {
//                return false;
//            }
//        }
        if (check(pers)){
            return false;
        }

        PermissionDialog
                .builder(getContext())
                .setOnClickListener(onClickListener)
                .setTitile(title)
                .setMsg(msg)
                .show();

        return true;
    }

    /**
     * 拒绝后的再次申请,弹出说明框
     *
     * @param view            dialog的自定义View 标题需要getTag为"title"， 描述内容是"msg",取消按钮是"cancel",确定按钮是"ok"
     * @param okView          自定义View中去设置的按钮
     * @param cancelView          取消的按钮
     * @param onClickListener 取消按钮的回调
     * @return
     */
    public boolean requestRationale(@NonNull View view, @NonNull View okView,@NonNull View cancelView, DialogInterface.OnClickListener onClickListener, @NonNull int requestCode, @NonNull String... pers) {
        Activity act = getActivity();
        if (act == null) {
            return false;
        }
        if (check(pers)){
            return false;
        }

        PermissionDialog
                .builder(getContext())
                .setOnClickListener(onClickListener)
                .setView(view)
                .setOkView(okView)
                .setCancelView(cancelView)
                .show();

        return true;
    }

    /**
     * @return
     */
    public abstract Context getContext();

    public abstract Activity getActivity();

    /**
     * 申请权限
     *
     * @param requestCode
     * @return
     */
    public abstract T request(@NonNull int requestCode, @NonNull String... pers);

}
