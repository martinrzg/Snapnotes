package com.example.martinruiz.snapnotes.DatabaseModel;

import java.util.Map;

/**
 * Created by Erik on 08/11/2017.
 */

public class Days {

    Map<String, Courses> courses;

    public Days() {
    }

    public Days(Map<String, Courses> courses) {
        this.courses = courses;
    }

    public Map<String, Courses> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Courses> courses) {
        this.courses = courses;
    }
}
