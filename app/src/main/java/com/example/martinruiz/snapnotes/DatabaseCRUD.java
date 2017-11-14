package com.example.martinruiz.snapnotes;

import android.util.Log;
import android.widget.Toast;

import com.example.martinruiz.snapnotes.DatabaseModel.Boards;
import com.example.martinruiz.snapnotes.DatabaseModel.BoardContent;
import com.example.martinruiz.snapnotes.DatabaseModel.Days;
import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.example.martinruiz.snapnotes.DatabaseModel.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Erik on 20/10/2017.
 */


public class DatabaseCRUD {


    public static final String BOARDS = "boards";
    public static final String CALENDAR = "calendar";
    public static final String COURSES = "courses";

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
                        Boards board = dataSnapshot.getValue(Boards.class);

                        if(board != null && board.getBoards() != null  ) {

                            // [START_EXCLUDE]
                            if (board == null) {

                                // Board is null, error out
                                Log.e(TAG, "Boards is unexpectedly null");
                            } else {
                                // Write new Board
                                if(!dataSnapshot.child(BOARDS).hasChild(boardContent.getName())){
                                    newBoard(mDatabase, board.getBoards(), boardContent);
                                }
                            }
                        }else {
                            //Initialize boards if is empty
                            Map<String, BoardContent> map = new HashMap<>();
                            newBoard(mDatabase, map, boardContent);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });


    }

    private static void newBoard(DatabaseReference mDatabase, Map<String, BoardContent> boards, BoardContent boardContent) {
        //Generate key and add it to the boards
       /* String key = mDatabase.child(BOARDS).push().getKey();
        boardContent.setId(key);*/

        //Insert the new board to the boards HashMap
        boards.put(boardContent.getName(), boardContent);

        mDatabase.child(BOARDS).child(boardContent.getName()).setValue(boardContent);
    }

    /**
     * Method to add new Calendar to the user board.
     *
     * @param mDatabase    Database reference to the user database, recommended use: mDatabase.child(mAuth.getUid().
     * @param courses Object USerCalendar that is going to be add to the database.
     */
    public static void writeNewCalendar(final DatabaseReference mDatabase, final Courses courses) {


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
                                newCalendar(mDatabase,board.getCalendar(), courses);

                            }
                        } else {
                            //Initialize boards if is empty

                            Map<String, Days> map = new HashMap<>();


                            mDatabase.child(CALENDAR).setValue(map);
                            newCalendar(mDatabase,map, courses);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });


    }

    private static void newCalendar(DatabaseReference mDatabase,Map<String, Days> days, Courses course) {


        mDatabase.child(CALENDAR).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get board value


                        if(dataSnapshot.child(course.getDay()).hasChild(COURSES)) {
                            Days courses = dataSnapshot.child(course.getDay()).getValue(Days.class);

                            // [START_EXCLUDE]
                            if (days == null) {

                                // Board is null, error out
                                Log.e(TAG, "Boards is unexpectedly null");
                            } else {

                                // Write new Board
                                newCourse(mDatabase,days, courses.getCourses(), course);

                            }
                        }else {
                            Map<String, Courses> map = new HashMap<>();
                            newCourse(mDatabase,days, map, course);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }
                });

    }

    private static void newCourse(DatabaseReference mDatabase, Map<String,Days> days, Map<String, Courses> coursesMap, Courses course) {
        coursesMap.put(course.getName(), course);
        Days courses = new Days(coursesMap);
        days.put(course.getDay(),courses);
        mDatabase.child(CALENDAR).child(course.getDay()).setValue(courses);
    }

    public static void getBoardHour(DatabaseReference mDatabase)  {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        SimpleDateFormat day = new SimpleDateFormat("E");
        DateFormat df = new SimpleDateFormat("HH:mm");

        mDatabase.child(CALENDAR).child(day.format(currentTime)).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get board value

                        Log.d("entra","entra");
                        Days days = dataSnapshot.getValue(Days.class);

                        Date currentHour = new Date();
                        try {
                            currentHour = df.parse(format.format(currentTime));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                            // [START_EXCLUDE]
                            if (days == null) {

                                // Board is null, error out
                                Log.e(TAG, "Days is unexpectedly null");
                            } else {

                                // Write new Board
                                for (String key: days.getCourses().keySet()){
                                    Courses courses = days.getCourses().get(key);

                                    Date startDate = new Date();
                                    Date endDate= new Date();
                                    try {
                                        startDate = df.parse(courses.getStart());
                                        String newDateString = df.format(startDate);
                                        endDate = df.parse(courses.getEnd());
                                        System.out.println(newDateString);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Log.d("horas: ", startDate+" "+endDate+""+currentHour );
                                    if(currentHour.before(endDate) && currentHour.after(startDate)){
                                        Log.d("hora","si hay: "+courses.getName());

                                    }
                                }
                            }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getPost:onCancelled", databaseError.toException());

                    }

                });
    }
}
