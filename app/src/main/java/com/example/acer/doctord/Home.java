package com.example.acer.doctord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button appoin;
    private Button signUp;
    private Button signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        appoin = findViewById(R.id.appo);
        signUp =findViewById(R.id.docSIgnup);


        appoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appoPage();
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }




    public  void  appoPage(){
        Intent appoPage = new Intent(Home.this,AppoPage.class);
        startActivity(appoPage);

    }

    public  void  signUp(){
        Intent signup = new Intent(Home.this,SignUp.class);
        startActivity(signup);

    }
}
