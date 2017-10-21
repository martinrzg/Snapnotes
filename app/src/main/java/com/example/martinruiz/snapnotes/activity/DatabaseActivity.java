package com.example.martinruiz.snapnotes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.martinruiz.snapnotes.DatabaseCRUD;
import com.example.martinruiz.snapnotes.DatabaseModel.Boards;
import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.example.martinruiz.snapnotes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Boards boards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mAuth = FirebaseAuth.getInstance();


        mDatabase = FirebaseDatabase.getInstance().getReference();


        DatabaseCRUD.writeNewBoard(mDatabase, mAuth.getCurrentUser().getUid(),"Base de datos");


    }


}
