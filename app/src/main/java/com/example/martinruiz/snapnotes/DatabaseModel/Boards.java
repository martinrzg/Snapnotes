package com.example.martinruiz.snapnotes.DatabaseModel;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Erik on 20/10/2017.
 */

public class Boards implements Serializable{


    public Map<String, Object> boards;
    public Map<String, Days> calendar;

    public Boards() {

    }

    public Boards(Map<String, Object> boards, Map<String, Days> days) {
        this.boards = boards;
        this.calendar = days;
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

    public Map<String, Days> getCalendars() {
        return calendar;
    }

    public void setCalendars(Map<String, Days> calendars) {
        this.calendar = calendars;
    }
}
