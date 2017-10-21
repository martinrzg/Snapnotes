package com.example.martinruiz.snapnotes;

import com.example.martinruiz.snapnotes.DatabaseModel.Boards;
import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Erik on 20/10/2017.
 */

public class DatabaseCRUD {

    static Boards boards;

    public static void writeNewBoard(DatabaseReference mDatabase, String userId, String name) {
        String key = mDatabase.child("Boards").push().getKey();

        boards = new Boards();

        boards.setId(key);
        boards.setName(name);

        mDatabase.child(userId).child("boards").child(key).child(name).child("notes");


    }

    public static void writeNote(DatabaseReference mDatabase, String id, Notes notes){

        String key1 = mDatabase.child(id).push().getKey();

        mDatabase.child(id).child("boards").child(boards.getId()).child(boards.getName()).child("notes").child(key1).setValue(true);
        mDatabase.child(id).child("notes").child(key1).setValue(notes);
    }

    private void setCurretBoard(String id){
    }
}
