package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    Splash splash = new Splash();
    private Context context;
    public FingerprintHandler(Context context){
        this.context = context;
    }
    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was an Auth error."+errString,false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Auth Failed",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error"+helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        splash.access = true;
        this.update("Access Granted",true);
    }

    private void update(String s, boolean b) {
        TextView paraLabel = (TextView) ((Activity)context).findViewById(R.id.txt_msg);
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.fingerprintImg);

        paraLabel.setText(s);

        if(b == false){
            paraLabel.setTextColor(ContextCompat.getColor(context,R.color.colorRed));
        }else{
            paraLabel.setTextColor(ContextCompat.getColor(context,R.color.colorGreen));
            imageView.setImageResource(R.drawable.tick);
        }
    }
}
