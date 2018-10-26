package top.crossrun.util.permission.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;

import top.crossrun.util.permission.R;

/**
 *
 * 弹出说明框
 */
public class PermissionDialog {

    Context context;
    String titile;
    String msg;
    DialogInterface.OnClickListener onClickListener;
    View view;
    View okView;
    View cancelView;

    private PermissionDialog(Context context) {
        this.context = context;
    }

    public static PermissionDialog builder(Context context) {
        return new PermissionDialog(context);
    }

    public PermissionDialog setTitile(String titile) {
        this.titile = titile;
        return this;
    }

    public PermissionDialog setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public PermissionDialog setOnClickListener(DialogInterface.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public PermissionDialog setView(View view) {
        this.view = view;
        return this;
    }

    public PermissionDialog setOkView(View okView) {
        this.okView = okView;
        return this;
    }

    public PermissionDialog setCancelView(View cancelView) {
        this.cancelView = cancelView;
        return this;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        if (view != null) {
            builder.setView(view);
            final AlertDialog dialog = builder.create();
            okView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToAppSetting();
                }
            });
            cancelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(dialog, -1);
                }
            });
            dialog.show();
        } else {
            builder.setTitle(titile)
                    .setMessage(msg)
                    .setPositiveButton(R.string.pm_goto_setting, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goToAppSetting();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (onClickListener == null) {
                                dialog.dismiss();
                            } else {
                                onClickListener.onClick(dialog, which);
                            }
                        }
                    }).show();
        }
    }

    // 跳转到应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
