package com.techsol.cedc.METEFLA;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.vision.barcode.Barcode;

public class LogEntryActivityHospital extends AppCompatActivity {
    private static final String TAG = "LogEntryHospital";

    public static final String QRCodeObject = "QRCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_entry_hospital);

        Barcode data = getIntent().getParcelableExtra(QRCodeObject);
        Log.d(TAG, "QRCode read: " + data.displayValue);
    }
}
