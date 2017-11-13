package com.example.martinruiz.snapnotes.activity;

import java.util.ArrayList;

/**
 * Created by Erik on 07/11/2017.
 */

public class CalendarModel {

    String id;
    String name;

    public CalendarModel() {
    }

    public CalendarModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
