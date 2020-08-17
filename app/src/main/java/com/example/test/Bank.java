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

public class Bank extends AppCompatActivity {
    TextView titlepage,subtitlepage,completeName,userName,bankone;
    Button btnEdit,pay,history;
//
    String SHARED_PREFS = "sharedPrefs";
    String SHARED_PREFS2 = "sharedPrefs2";
    String userCompleteName = "";
    String getUserCompleteName;
    String userUserName = "";
    String getUserUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        //target element
        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        completeName = findViewById(R.id.completeName);
        userName = findViewById(R.id.userName);
        btnEdit = findViewById(R.id.btnEdit);
        bankone = findViewById(R.id.bankone);
        pay = findViewById(R.id.pay);
        history = findViewById(R.id.history);



        //import font
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/MRegular.ttf");

        //customize font
        titlepage.setTypeface(MRegular);
        subtitlepage.setTypeface(MLight);
        completeName.setTypeface(MRegular);
        userName.setTypeface(MRegular);
        btnEdit.setTypeface(MMedium);
        bankone.setTypeface(MMedium);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Bank.this,MainActivity.class);
                startActivity(a);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(Bank.this,QRPayment.class);
                startActivity(n);

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(Bank.this,History.class);
                startActivity(m);

            }
        });



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
