package com.example.martinruiz.snapnotes.DatabaseModel;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Erik on 20/10/2017.
 */

public class Boards implements Serializable{


    public Map<String, Object> boards;


    public Boards() {

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
}
