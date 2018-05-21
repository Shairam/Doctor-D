package com.example.acer.doctord;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Start extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeInt = new Intent(Start.this,Home.class);
                homeInt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeInt);
            }
        },2000);
    }
}
