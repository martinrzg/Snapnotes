package com.example.martinruiz.snapnotes.DatabaseModel;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Erik on 20/10/2017.
 */

public class Boards implements Serializable{


    public Map<String, Object> boards;
    public Map<String, UserCalendar> calendar;

    public Boards() {

    }


    public Boards(Map<String, Object> boards, Map<String, UserCalendar> calendar) {
        this.boards = boards;
        this.calendar = calendar;
    }

    public Boards(Map<String, Object> boards) {
        this.boards = boards;
    }

    public Map<String, Object> getBoards() {
        return boards;
    }

    public void setBoards(Map<String, Object> boards) {
        this.boards = boards;
    }

    public Map<String, UserCalendar> getCalendar() {
        return calendar;
    }

    public void setCalendar(Map<String, UserCalendar> calendar) {
        this.calendar = calendar;
    }
}
