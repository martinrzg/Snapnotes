package com.example.martinruiz.snapnotes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: Comment to start the activity you want for debug.
//        mAuth = FirebaseAuth.getInstance();
//        if(mAuth.getCurrentUser() == null){
//            Intent intent = new Intent(this, LogInActivity.class);
//            startActivity(intent);
//        }else{
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
        finish();
    }
}
