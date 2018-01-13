package com.example.martinruiz.snapnotes.DatabaseModel;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 31/10/2017.
 */

public class BoardContent {



    public String name;
    public Map<String, Notes> notes;



    public BoardContent() {
    }




    public BoardContent(String name) {
        this.name = name;
    }

    /**
     * Construct a BoardContent with id, name and HasMap of notes
     * @param name Name of the Board
     * @param notes HasMap of notes
     */
    public BoardContent( String name, Map<String, Notes> notes) {
        this.name = name;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Map<String, Notes> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, Notes> notes) {
        this.notes = notes;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("notes", notes);


        return result;
    }
}
