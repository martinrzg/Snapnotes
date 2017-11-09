package com.example.martinruiz.snapnotes.DatabaseModel;

/**
 * Created by Erik on 07/11/2017.
 */

public class Courses {

    private String name;
    private String day;
    private String start;
    private String end;

    public Courses() {
    }

    public Courses(String name, String day, String start, String end) {
        this.name = name;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
