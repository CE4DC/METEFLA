package com.techsol.cedc.METEFLA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLogin(View v) {
        Intent intent;
        SharedPreferences userPrefs = this.getSharedPreferences("userPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = userPrefs.edit();
        switch (v.getId()) {
            case R.id.login_doctor:
                editor.putInt("USERTYPE", 1);
                intent = new Intent(this, QRScanActivity.class);
                startActivity(intent);
                break;
            case R.id.login_technician:
                editor.putInt("USERTYPE", 2);
                intent = new Intent(this, QRScanActivity.class);
                startActivity(intent);
                break;
        }
        editor.apply();
    }
}
