package com.example.martinruiz.snapnotes.DatabaseModel;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 20/10/2017.
 */


public class Notes {

    public String id;
    public String url;
    public String text;
    public String day;


    public Notes() {
    }

    /**
     *Constructor to a new not.
     * @param id  Id of the note
     * @param url  Url of the note
     * @param text Text of the note
     * @param day Day of the note
     */
    public Notes( String id, String url, String text, String day) {
        this.id = id;
        this.url = url;
        this.text = text;
        this.day = day;

    }

    /**
     *Constructor to a new note without id.
     * @param url  Url of the note
     * @param text Text of the note
     * @param day Day of the note
     */
    public Notes( String url, String text, String day) {
        this.url = url;
        this.text = text;
        this.day = day;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("id", id);
        result.put("url", url);
        result.put("text", text);
        result.put("day", day);
        return result;
    }
}
