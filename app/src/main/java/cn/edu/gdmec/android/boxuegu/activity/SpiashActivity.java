package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gdmec.android.boxuegu.R;

public class SpiashActivity extends AppCompatActivity {
 private TextView tv_verson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiash);
        tv_verson=findViewById(R.id.tv_verson);
        try {
            PackageInfo info=getPackageManager().getPackageInfo(getPackageName(),0);
            tv_verson.setText("ver:"+info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(SpiashActivity.this,MainActivity.class);
                startActivity(intent);
                SpiashActivity.this.finish();
            }
        };
        timer.schedule(task,3000);
    }
}
