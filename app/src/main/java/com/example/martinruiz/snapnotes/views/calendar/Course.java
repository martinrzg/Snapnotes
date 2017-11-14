package com.example.martinruiz.snapnotes.views.calendar;

/**
 * Created by isaac on 11/14/17.
 */

public class Course {

    private static int INITIAL_HOUR = 6;

    private String name;
    private Day day;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    public Course(String name, Day day, int startHour, int startMinute, int endHour, int endMinute) {
        this.name = name;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public int getDeepInDP(){
        int initial = 42;
        int baseDPperHour = 50;
        return initial + (startHour - INITIAL_HOUR) * baseDPperHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }
}
