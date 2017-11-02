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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = DatabaseActivity.class.getSimpleName();
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

        DatabaseCRUD.writeNewNote(mDatabase.child(mAuth.getUid()),new Notes("wwww","ffasdfwverfd","34fsaf"), "-Kxu9MsTs8L5u9Clf-va");
        //DatabaseCRUD.writeNewBoard(mDatabase.child(mAuth.getUid()),new BoardContent("Base de datos", new HashMap<String, Object>()));


    }

    @Override
    protected void onStart() {
        super.onStart();


        //Example get All elements and add a listener
       /* mDatabase.child(mAuth.getUid()).child("boards").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count ", "" + snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    BoardContent boards = postSnapshot.getValue(BoardContent.class);
                    Log.e("Get Data", boards.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("The read failed: ", "Fail");


            }
        });*/

       //Example to get the notes from a board
        /*mDatabase.child(mAuth.getUid()).child("boards").child("-Kxu9MsTs8L5u9Clf-va").child("notes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count ", "" + snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Notes notes = postSnapshot.getValue(Notes.class);
                    Log.e("Get Data", notes.getId());
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("The read failed: ", "Fail");


            }
        });*/
    }
}
