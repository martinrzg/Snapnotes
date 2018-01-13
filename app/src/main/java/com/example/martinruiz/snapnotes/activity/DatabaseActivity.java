package com.example.martinruiz.snapnotes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.martinruiz.snapnotes.DatabaseModel.BoardContent;
import com.example.martinruiz.snapnotes.DatabaseModel.Boards;
import com.example.martinruiz.snapnotes.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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


//        DatabaseCRUD.writeNewNote(mDatabase.child(mAuth.getUid()),new Notes("id","wwww","ffasdfwverfd","34fsaf"), "Graficas");
        //DatabaseCRUD.writeNewBoard(mDatabase.child(mAuth.getUid()),new BoardContent("Base de datos", new HashMap<String, Object>()));
//        DatabaseCRUD.writeNewCalendar(mDatabase.child(mAuth.getUid()), new Courses("Seguridad", "jueves", "11:30", "1:00"));
//        DatabaseCRUD.writeNewCalendar(mDatabase.child(mAuth.getUid()), new Courses("Graficas", "viernes", "11:30", "1:00"));
//        DatabaseCRUD.writeNewCalendar(mDatabase.child(mAuth.getUid()), new Courses("Seguridad", "martes", "11:30", "1:00"));


    }


    @Override
    protected void onStart() {
        super.onStart();


        //Example get All elements and add a listener
        mDatabase.child(mAuth.getUid()).child("boards").addValueEventListener(new ValueEventListener() {
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
        });

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
