package com.techsol.cedc.METEFLA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;

        SharedPreferences userPrefs = this.getSharedPreferences(getResources().getString(R.string.PREF_KEY_USER), MODE_PRIVATE);
        int userType = userPrefs.getInt(getResources().getString(R.string.PREF_KEY_USER_TYPE), 0);
        if(userType != 0) {
            intent = new Intent(this, QRScanActivity.class);
        }
        else {
            intent = new Intent(this, AccountCreateActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
