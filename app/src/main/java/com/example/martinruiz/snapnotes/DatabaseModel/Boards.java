package com.example.martinruiz.snapnotes.DatabaseModel;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Erik on 20/10/2017.
 */

public class Boards implements Serializable{


    public Map<String, BoardContent> boards;
    public Map<String, Days> calendar;

    public Boards() {

    }

    public Boards(Map<String, BoardContent> boards, Map<String, Days> calendar) {
        this.boards = boards;
        this.calendar = calendar;
    }

    public Map<String, BoardContent> getBoards() {
        return boards;
    }

    public void setBoards(Map<String, BoardContent> boards) {
        this.boards = boards;
    }

    public Map<String, Days> getCalendar() {
        return calendar;
    }

    public void setCalendar(Map<String, Days> calendar) {
        this.calendar = calendar;
    }
}
