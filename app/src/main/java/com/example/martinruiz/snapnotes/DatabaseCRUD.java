package com.example.martinruiz.snapnotes;

import android.util.Log;

import com.example.martinruiz.snapnotes.DatabaseModel.Boards;
import com.example.martinruiz.snapnotes.DatabaseModel.BoardContent;
import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Erik on 20/10/2017.
 */

public class DatabaseCRUD {

    public static void writeNewNote(final DatabaseReference mDatabase, final Notes notes, final String boardId){
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get board value
                        Boards board = dataSnapshot.getValue(Boards.class);

                        // [START_EXCLUDE]
                        if (board == null) {
                            // Board is null, error out
                            Log.e(TAG, "Boards is unexpectedly null");
                        } else {
                            // Write new Board
//                            Map<String,Object> hash = board.getBoards();
//                            Log.d(TAG, hash.containsKey("-Kxp8hyF_WhEP4mfELV9")+"");
//                            BoardContent boardContent = (BoardContent) hash.get("-Kxp8hyF_WhEP4mfELV9");
//                            boa
//                            Log.d(TAG,boardContent.getName());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });
    }

    private static void newNote(DatabaseReference mDatabase, Map<String, Object> boardContent, Notes notes, String boardID) {


    }

    public static void writeNewBoard(final DatabaseReference mDatabase, final BoardContent boardContent) {

        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get board value
                        Boards board = dataSnapshot.getValue(Boards.class);

                        // [START_EXCLUDE]
                        if (board == null) {
                            // Board is null, error out
                            Log.e(TAG, "Boards is unexpectedly null");
                        } else {
                            // Write new Board
                            newBoard(mDatabase, board.getBoards(), boardContent);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });



    }

    private static void newBoard(DatabaseReference mDatabase, Map<String, Object> boards, BoardContent boardContent) {
        //Generate key and add it to the boards
        String key = mDatabase.child("boards").push().getKey();
        boardContent.setId(key);

        //Insert the new board to the boards HashMap
        boards.put(key, boardContent);

        mDatabase.child("boards").setValue(boards);
    }
}
