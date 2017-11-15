package com.example.martinruiz.snapnotes.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private SharedPreferences prefs ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAuth = FirebaseAuth.getInstance();
        //FirebaseAuth.getInstance().signOut();
        if(mAuth.getCurrentUser() == null){
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
//            Log.d("Splash","login");
        }else{
            prefs = this.getSharedPreferences("com.example.martinruiz.snapnotes."+mAuth.getCurrentUser().getUid(), Context.MODE_PRIVATE);
            if(!prefs.getBoolean("calendar",false)) {
                Intent intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
//                Log.d("Splash","calendar");
            }else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
//                Log.d("Splash","main");
            }
        }
        finish();
    }
}
