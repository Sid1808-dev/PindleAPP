package com.example.test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.hardware.biometrics.BiometricManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;

    TextView titlepage,subtitlepage,completeName,userName;
    Button btnSave,btnCancel;
    ImageView imageView;
    Button btnUploadpic;

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

        //External Strorage read for Image update

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST);
        }

        imageView = findViewById(R.id.imageView);
        btnUploadpic = findViewById(R.id.btnUpload);

        btnUploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i1,RESULT_LOAD_IMAGE);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String completename = completeName.getText().toString();
                if(username.isEmpty()){
                    userName.setError("Please enter a username");
                    userName.requestFocus();
                }else if(completename.isEmpty()){
                    completeName.setError("Please enter a valid Complete name");
                    completeName.requestFocus();
                }

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

        //Load data and update data on mainActivity
        loadData();
        updateData();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"Permission not Granted",Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                }
        }
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
