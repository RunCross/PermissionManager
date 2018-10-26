package top.crossrun.util.permission;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

import top.crossrun.util.permission.task.PermissionRequestTaskStartAcitivity;

public class PermissionRequestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int requestCode = getIntent().getIntExtra("requestCode",0);
        String[] pers = getIntent().getStringArrayExtra("permissions");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(pers,requestCode);
        } else {
            ActivityCompat.requestPermissions(this, pers, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent data = new Intent();
        ArrayList<String> allow = new ArrayList<>();
        ArrayList<String> disallow = new ArrayList<>();
        for (int i =0;i<grantResults.length;i++){
            if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                allow.add(permissions[i]);
            }else {
                disallow.add(permissions[i]);
            }
        }
        data.putExtra("allow",allow);
        data.putExtra("disallow",disallow);
        setResult(RESULT_OK,data);
        finish();
    }
}
