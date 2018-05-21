package com.example.acer.doctord;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btn1;
    private Button btn2;
    private EditText etemail, etpassword;
    ProgressBar pb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etemail = findViewById(R.id.email);
        btn1 = findViewById(R.id.signingup);
        btn2 = findViewById(R.id.signin);
        etpassword =findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.progressBar2);


         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 registerUser();
             }
         });

         btn2
                 .setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         signInUser();
                     }
                 });


    }


    public void  registerUser(){
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

        if (email.isEmpty()){
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            etpassword.setError("Password is required");
            etpassword.requestFocus();
            return;
        }

        pb.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pb.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Registered Sucessfully",Toast.LENGTH_SHORT).show();
                    Intent appInt = new Intent(SignUp.this,Appointment_List.class);

                    startActivity(appInt);
                }

                else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(SignUp.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(getApplicationContext(),"User Registered Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signInUser(){

        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

        if (email.isEmpty()){
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemail.setError("Email is invalid");
            etemail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            etpassword.setError("Password is required");
            etpassword.requestFocus();
            return;
        }

        pb.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pb.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                                Intent appInt = new Intent(SignUp.this,Appointment_List.class);

                                startActivity(appInt);


                        }

                        else if (task.getException() instanceof FirebaseAuthInvalidUserException){
                            Toast.makeText(SignUp.this, "You are not Signed up",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                            Toast.makeText(SignUp.this, "Your Password is wrong",
                                    Toast.LENGTH_SHORT).show();
                            etpassword.setError("Password is Wrong");
                            etpassword.requestFocus();

                        }

                        else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


}
