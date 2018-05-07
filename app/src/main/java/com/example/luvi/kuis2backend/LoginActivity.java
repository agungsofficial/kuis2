package com.example.luvi.kuis2backend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnDaftar (View v){
        Intent goDaftar = new Intent (LoginActivity.this, DaftarActivity.class);
        startActivity(goDaftar);
    }

    public void btnLogin (View v){
        Intent goLogin = new Intent (LoginActivity.this, MainActivity.class);
        startActivity(goLogin);
    }
}
