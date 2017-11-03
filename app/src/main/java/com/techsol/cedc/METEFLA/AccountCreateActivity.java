package com.techsol.cedc.METEFLA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AccountCreateActivity extends AppCompatActivity {
    private EditText inputFirstName;
    private EditText inputLastName;
    private Spinner inputUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);

        inputFirstName = (EditText) findViewById(R.id.account_create_input_name_first);
        inputLastName = (EditText) findViewById(R.id.account_create_input_name_last);
        inputUserType = (Spinner) findViewById(R.id.account_create_input_user_type);
    }

    boolean isEditTextEmpty(EditText eTxt) {
        return eTxt.getText().toString().trim().length() == 0;
    }

    public void onClickDone(View v) {
        // validate form
        boolean emptyFirstName = isEditTextEmpty(inputFirstName);
        boolean emptyLastName = isEditTextEmpty(inputLastName);
        String statusText = "";
        if(emptyFirstName || emptyLastName) {
            // some input was empty, show error in status
            if (emptyFirstName) {
                statusText += getResources().getString(R.string.account_create_status_error_firstname) + "\n";
            }
            if (emptyLastName) {
                statusText += getResources().getString(R.string.account_create_status_error_lastname);
            }
        }
        else {
            // no problems with form
            String firstName = inputFirstName.getText().toString();
            String lastName = inputLastName.getText().toString();
            int userTypeIdx = inputUserType.getSelectedItemPosition();
            String[] userTypes = getResources().getStringArray(R.array.account_create_choices_user_types);
            int userType = Integer.valueOf(userTypes[userTypeIdx]);

            // store inputs in sharedPrefs
            SharedPreferences userPrefs = this.getSharedPreferences(getResources().getString(R.string.PREF_KEY_USER), MODE_PRIVATE);
            SharedPreferences.Editor editor = userPrefs.edit();
            editor.putString(getResources().getString(R.string.PREF_KEY_USER_NAME_FIRST), firstName);
            editor.putString(getResources().getString(R.string.PREF_KEY_USER_NAME_LAST), lastName);
            editor.putInt(getResources().getString(R.string.PREF_KEY_USER_TYPE), userType);
            editor.apply();

            //
            Intent intent;
            intent = new Intent(this, QRScanActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
