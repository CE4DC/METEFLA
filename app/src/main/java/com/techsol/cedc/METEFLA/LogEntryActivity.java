package com.techsol.cedc.METEFLA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;

import com.google.android.gms.vision.barcode.Barcode;

public class LogEntryActivity extends AppCompatActivity{
    private static final String TAG = "LogEntry";

    public static final String QRCodeObject = "QRCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_entry);

        Barcode data = getIntent().getParcelableExtra(QRCodeObject);
        Log.d(TAG, "QRCode read: " + data.displayValue);

        ScrollView mainContentHolder = (ScrollView) findViewById(R.id.log_entry_content_holder);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainContentView;
        SharedPreferences userPrefs = this.getSharedPreferences(getResources().getString(R.string.PREF_KEY_USER), MODE_PRIVATE);
        int userType = userPrefs.getInt(getResources().getString(R.string.PREF_KEY_USER_TYPE), 0);
        if(userType == 2) {
            mainContentView = inflater.inflate(R.layout.log_entry_technician, mainContentHolder, false);
        }
        else {
            mainContentView = inflater.inflate(R.layout.log_entry_hospital, mainContentHolder, false);
        }

        mainContentHolder.addView(mainContentView);
    }

    public void onClickCancel(View v) {
        // check if there is input and confirm cancel
        finish();
    }

    public void onClickOk(View v) {
        // validate input?
    }
}
