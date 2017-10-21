package com.example.martinruiz.snapnotes.DatabaseModel;

/**
 * Created by Erik on 20/10/2017.
 */

public class Boards {
    private String name;
    private String id;


    public Boards() {

    }

    public Boards(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
