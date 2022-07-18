package com.ulas.neoverde2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ulas.neoverde2.R;

public class SPlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash();
    }

    public void splash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SPlashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}