package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Bank extends AppCompatActivity {
    TextView titlepage,subtitlepage,completeName,userName;
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
        final TextView tv1 = (TextView)findViewById(R.id.tv1);

        pay = findViewById(R.id.pay);
        history = findViewById(R.id.history);
        final Random myRandom = new Random();

        Button btnRefresh = (Button) findViewById(R.id.btnRefresh);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long leftLimit1 = 1111L;
                long rightLimit1 = 9999L;

                long generatedLong1 = leftLimit1 + (long) (Math.random() * (rightLimit1 - leftLimit1));
                long generatedLong2 = leftLimit1 + (long) (Math.random() * (rightLimit1 - leftLimit1));
                long generatedLong3 = leftLimit1 + (long) (Math.random() * (rightLimit1 - leftLimit1));
                long generatedLong4 = leftLimit1 + (long) (Math.random() * (rightLimit1 - leftLimit1));

                tv1.setText(String.valueOf(generatedLong1) + "-"+String.valueOf(generatedLong2)+"-"+String.valueOf(generatedLong3)+"-"+String.valueOf(generatedLong4));


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
        btnEdit.setTypeface(MMedium);
        tv1.setTypeface(MMedium);


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


        //Loading the data from MainActivity to display on the Card
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
