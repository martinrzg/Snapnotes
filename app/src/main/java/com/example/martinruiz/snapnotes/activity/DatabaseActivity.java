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


//        Map<String, Object> map = new HashMap<>();
//        map.put("key", new BoardContent("ds",new HashMap<String, Object>()));
//        Boards boards = new Boards(map);
//
//        mDatabase.child(mAuth.getUid()).child("boards").setValue(boards);

          DatabaseCRUD.writeNewNote(mDatabase.child(mAuth.getUid()),new Notes("ff","ff","ff","ff"), "v");
//        DatabaseCRUD.writeNewBoard(mDatabase.child(mAuth.getUid()),new BoardContent("Graficas", new HashMap<String, Object>()));


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
