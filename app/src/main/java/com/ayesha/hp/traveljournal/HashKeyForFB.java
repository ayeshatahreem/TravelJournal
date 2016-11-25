package com.ayesha.hp.traveljournal;

/**
 * Created by HP on 24/11/16.
 */
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class HashKeyForFB extends AppCompatActivity {
    TextView tv_keyhash;
    Button btn_keyhash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hashkeyforfb);
        tv_keyhash= (TextView) findViewById(R.id.tv_keyhash);
        btn_keyhash= (Button) findViewById(R.id.btn_keyhash);
        btn_keyhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printHahKey();
            }
        });

    }
    /**
     * Method for printing Hash key to logcat and TextView
     */
    void printHahKey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.ayesha.hp.traveljournal",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                tv_keyhash.setText(Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }
    }
}