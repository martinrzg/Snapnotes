package com.example.martinruiz.snapnotes.activity;

import android.media.MediaActionSound;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.martinruiz.snapnotes.DatabaseCRUD;
import com.example.martinruiz.snapnotes.DatabaseModel.BoardContent;
import com.example.martinruiz.snapnotes.DatabaseModel.Boards;
import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.example.martinruiz.snapnotes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DatabaseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mNotes;
    private Boards boards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        DatabaseCRUD.writeNewNote(mDatabase.child(mAuth.getUid()),new Notes("sdf","fd","34"), "-Kxu9MsTs8L5u9Clf-va");
        //DatabaseCRUD.writeNewBoard(mDatabase.child(mAuth.getUid()),new BoardContent("Base de datos", new HashMap<String, Object>()));


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
