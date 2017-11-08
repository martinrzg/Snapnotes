package com.example.martinruiz.snapnotes;

import android.util.Log;

import com.example.martinruiz.snapnotes.DatabaseModel.Boards;
import com.example.martinruiz.snapnotes.DatabaseModel.BoardContent;
import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.example.martinruiz.snapnotes.DatabaseModel.UserCalendar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static android.content.ContentValues.TAG;

/**
 * Created by Erik on 20/10/2017.
 */


public class DatabaseCRUD {

    private static final String BOARDS = "boards";
    private static final String CALENDAR = "calendar";

    /**
     * Add new note to the selected board.
     *
     * @param mDatabase Database reference to the user database, recommended use: mDatabase.child(mAuth.getUid().
     * @param note      Note object that is going to be add to the notes.
     * @param boardId   Id of the board where the note will be added.
     */
    public static void writeNewNote(final DatabaseReference mDatabase, final Notes note, final String boardId) {
        mDatabase.child("boards").child(boardId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get BoardContent value
                        BoardContent board = dataSnapshot.getValue(BoardContent.class);

                        if (board == null) {
                            // Note is null, error out
                            //If id is wrong could get this
                            Log.e(TAG, "Note is unexpectedly null");
                        } else {
                            //Send the data to add the note ot the database.
                            newNote(mDatabase, note, boardId, board);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });
    }

    private static void newNote(DatabaseReference mDatabase, Notes note, String boardId, BoardContent boardContent) {
        //Get the key for the new note
        String key = mDatabase.child("boards").child(boardId).push().getKey();

        if (boardContent.getNotes() == null) {

            note.setId(key);                                                      //Add the id to the note object.
            Map<String, Object> notes = new HashMap();                            //If the notes is empty initialize the HashMap for the notes.
            notes.put(key, note);                                                 //Add th new note to the HashMap
            boardContent.setNotes(notes);                                         //Add the notes HashMap to the boardContent
            mDatabase.child("boards").child(boardId).setValue(boardContent);      //Update the data of the database
        } else {
            note.setId(key);                                                      //Add the id to the note object.
            boardContent.getNotes().put(key, note);                               //Add th new note to the HashMap
            mDatabase.child(BOARDS).child(boardId).setValue(boardContent);      //Update the data of the database
        }


    }

    /**
     * Method to add new Board to the user board.
     *
     * @param mDatabase    Database reference to the user database, recommended use: mDatabase.child(mAuth.getUid().
     * @param boardContent Object BoardContent that is going to be add to the database.
     */
    public static void writeNewBoard(final DatabaseReference mDatabase, final BoardContent boardContent) {


        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get board value
                        if (dataSnapshot.hasChild(BOARDS)) {

                            Boards board = dataSnapshot.getValue(Boards.class);

                            // [START_EXCLUDE]
                            if (board == null) {

                                // Board is null, error out
                                Log.e(TAG, "Boards is unexpectedly null");
                            } else {
                                // Write new Board
                                newBoard(mDatabase, board.getBoards(), boardContent);
                            }
                        } else {
                            //Initialize boards if is empty
                            Map<String, Object> map = new HashMap<>();
                            newBoard(mDatabase, map, boardContent);
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
        String key = mDatabase.child(BOARDS).push().getKey();
        boardContent.setId(key);

        //Insert the new board to the boards HashMap
        boards.put(key, boardContent);

        mDatabase.child(BOARDS).setValue(boards);
    }

    /**
     * Method to add new Board to the user board.
     *
     * @param mDatabase    Database reference to the user database, recommended use: mDatabase.child(mAuth.getUid().
     * @param userCalendar Object USerCalendar that is going to be add to the database.
     */
    public static void writeNewCalendar(final DatabaseReference mDatabase, final UserCalendar userCalendar) {


        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get board value
                        if (dataSnapshot.hasChild(CALENDAR)) {

                            Boards board = dataSnapshot.getValue(Boards.class);

                            // [START_EXCLUDE]
                            if (board == null) {

                                // Board is null, error out
                                Log.e(TAG, "Boards is unexpectedly null");
                            } else {
                                // Write new Board
                                Log.d(TAG,"holi2");
                                newCalendar(mDatabase, board.getCalendar(), userCalendar);

                            }
                        } else {
                            //Initialize boards if is empty
                            Log.d(TAG,"holi");
                            Map<String, UserCalendar> map = new HashMap<>();
                            newCalendar(mDatabase, map, userCalendar);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });


    }

    private static void newCalendar(DatabaseReference mDatabase, Map<String, UserCalendar> calendars, UserCalendar userCalendar) {


        //Insert the new board to the boards HashMap
        calendars.put(userCalendar.getName(), userCalendar);

        mDatabase.child(CALENDAR).setValue(calendars);
    }
}
