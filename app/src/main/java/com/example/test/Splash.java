package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {
    private  TextView msg_text;
    private  Button login_btn;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private Cipher cipher;

    private KeyStore keyStore;
    private String KEY_NAME="AndroidKey";

    public boolean access = false;

    //Splash Screen (Pindle)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread((new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(Integer.MAX_VALUE);
                    finish();

                    Intent i = new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
        thread.start();
    //Now Enabling fingerprint sensor functionality
    //Creating msg and select button
        msg_text = findViewById(R.id.txt_msg);
        login_btn = findViewById(R.id.login_btn);

    //Check if user can use fingerprint sensor or not

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
            if(!fingerprintManager.isHardwareDetected()){
                Toast toast = Toast.makeText(getApplicationContext(),"Fingerprint sensor not found",Toast.LENGTH_SHORT);
                toast.show();
            }else if(ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)!= PackageManager.PERMISSION_GRANTED){
                Toast toast = Toast.makeText(getApplicationContext(),"Permission not granted to fingerprint scanner",Toast.LENGTH_SHORT);
                toast.show();
            }else if(!keyguardManager.isKeyguardSecure()){
                Toast toast = Toast.makeText(getApplicationContext(),"You should have a password protected device",Toast.LENGTH_SHORT);
                toast.show();
            }else if(!fingerprintManager.hasEnrolledFingerprints()){
                Toast toast = Toast.makeText(getApplicationContext(),"Add at least one fingerprint",Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Place your finger on fingerprit scanner",Toast.LENGTH_SHORT);
                toast.show();
                generateKey();

                if(cipherInit()) {
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                    fingerprintHandler.startAuth(fingerprintManager, cryptoObject);
                }
            }

        }
        login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openMainActivity();
                }
            });



    }

    public void openMainActivity(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }
    private void generateKey() {

        try {

            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();

        } catch (KeyStoreException | IOException | CertificateException
                | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | NoSuchProviderException e) {

            e.printStackTrace();

        }

    }

    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }


        try {

            keyStore.load(null);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            return true;

        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }

    }
}
