package top.crossrun.util.permissionmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.crossrun.util.permission.PermissionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionManager.with(this);

    }
}
