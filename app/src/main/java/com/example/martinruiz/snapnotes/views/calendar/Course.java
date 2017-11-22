package com.example.martinruiz.snapnotes.views.calendar;

import android.view.View;

import java.io.Serializable;

/**
 * Created by isaac on 11/14/17.
 */

public class Course implements Serializable {


    private String name;
    private Day day;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    public Course(String name, Day day, String start, String end) {

        String s[] = start.split(":");
        String e[] = end.split(":");


        this.name = name;
        this.day = day;
        this.startHour = Integer.parseInt(s[0]);
        this.startMinute = Integer.parseInt(s[1]);
        this.endHour = Integer.parseInt(e[0]);
        this.endMinute = Integer.parseInt(e[1]);
    }

    public int getMarginInDp(){
        float start = startHour + (float)startMinute/60;
        int initial = 32;
        int baseDPperHour = 50;
        return initial + (int) (start * baseDPperHour);
    }

    public int getCellHeight(){
        float start = startHour + (float)startMinute/60;
        float end = endHour + (float)endMinute/60;
        int baseDPperHour = 50;
        int height = (int) ((end - start) * baseDPperHour);
        return height;
    }

    public String get24hStartTime(){
        String h = startHour/10 == 0 ? "0" : "";
        String m = startMinute/10 == 0 ? "0" : "";

        return h + startHour+":"+startMinute+m;
    }

    public String get24hEndTime(){
        String h = endHour/10 == 0 ? "0" : "";
        String m = endMinute/10 == 0 ? "0" : "";

        return h + endHour+":"+endMinute+m;
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
