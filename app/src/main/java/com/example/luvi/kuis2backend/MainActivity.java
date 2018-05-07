package com.example.luvi.kuis2backend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnSaldo (View v){
        Intent goSaldo = new Intent (MainActivity.this, SaldoActivity.class);
        startActivity(goSaldo);
    }

    public void btnTransfer (View v){
        Intent goTransfer = new Intent (MainActivity.this, TransferActivity.class);
        startActivity(goTransfer);
    }



}
