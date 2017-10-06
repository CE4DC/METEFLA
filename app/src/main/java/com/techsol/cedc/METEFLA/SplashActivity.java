package com.techsol.cedc.METEFLA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences userPrefs = this.getSharedPreferences("userPrefs", MODE_PRIVATE);
        int userType = userPrefs.getInt("USERTYPE", 0);
        Intent intent;
        switch (userType) {
            case 1:
                intent = new Intent(this, QRScanActivity.class);
                break;
            case 2:
                intent = new Intent(this, QRScanActivity.class);
                break;
            case 0:
            default:
                intent = new Intent(this, LoginActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }
}
