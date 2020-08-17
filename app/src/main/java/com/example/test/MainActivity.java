package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView titlepage,subtitlepage,completeName,userName;
    Button btnSave,btnCancel;

//    //set preferences for data
    String SHARED_PREFS = "sharedPrefs";
    String SHARED_PREFS2 = "sharedPrefs2";
    String userCompleteName = "";
    String getUserCompleteName;
    String userUserName = "";
    String getUserUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //target element
        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        completeName = findViewById(R.id.completeName);
        userName = findViewById(R.id.userName);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save Data
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(userCompleteName,completeName.getText().toString());
                editor.apply();
                SharedPreferences sharedPreferencesII = getSharedPreferences(SHARED_PREFS2,MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferencesII.edit();
                editor2.putString(userUserName,userName.getText().toString());
                editor2.apply();

                Intent i = new Intent(MainActivity.this,Bank.class);
                startActivity(i);
            }
        });

        //import font
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/MRegular.ttf");

        //customize font
        titlepage.setTypeface(MRegular);
        subtitlepage.setTypeface(MLight);
        completeName.setTypeface(MRegular);
        userName.setTypeface(MRegular);

        btnSave.setTypeface(MMedium);
        btnCancel.setTypeface(MLight);

        loadData();
        updateData();


    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences sharedPreferencesII = getSharedPreferences(SHARED_PREFS2,MODE_PRIVATE);

        getUserCompleteName = sharedPreferences.getString(userCompleteName,"");
        getUserUserName = sharedPreferencesII.getString(userUserName,"");
    }

    public void updateData(){
        completeName.setText(getUserCompleteName);
        userName.setText(getUserUserName);
    }

}
