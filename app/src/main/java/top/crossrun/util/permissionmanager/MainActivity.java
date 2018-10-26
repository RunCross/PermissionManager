package top.crossrun.util.permissionmanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.crossrun.util.permission.PermissionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionManager
                .with(this)
//                .request(1,Manifest.permission.CAMERA);
        .requestRationale("权限申请","拍照",null,1,Manifest.permission.CAMERA);
    }
}
