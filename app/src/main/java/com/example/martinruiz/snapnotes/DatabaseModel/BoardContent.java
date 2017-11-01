package com.example.martinruiz.snapnotes.DatabaseModel;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 31/10/2017.
 */

public class BoardContent {

    public String id;
    public String name;
    public Map<String, Object> notes;

    public BoardContent() {
    }

    public BoardContent(String name, Map<String, Object> notes) {
        this.name = name;
        this.notes = notes;
    }

    public BoardContent(String name, String id, Map<String, Object> notes) {
        this.name = name;
        this.id = id;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, Object> notes) {
        this.notes = notes;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("notes", notes);


        return result;
    }
}
