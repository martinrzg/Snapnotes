package com.example.martinruiz.snapnotes.DatabaseModel;

/**
 * Created by Erik on 20/10/2017.
 */

public class Notes {

    public String url;
    public String texto;
    public String day;

    public Notes() {
    }

    public Notes(String url, String texto, String day) {
        this.url = url;
        this.texto = texto;
        this.day = day;
    }
}
