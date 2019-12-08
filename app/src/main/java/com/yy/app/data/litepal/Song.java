package com.yy.app.data.litepal;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Song extends LitePalSupport {

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private int duration2;

    @Column(ignore = true)
    private String uselessField;

    private Album album;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDuration(int duration2) {
        this.duration2 = duration2;
    }


    public void setUselessField(String uselessField) {
        this.uselessField = uselessField;
    }


    public void setAlbum(Album album) {
        this.album = album;
    }


    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                "duration2='" + duration2 + '\'' +
                ", uselessField='" + uselessField + '\'' +
                ", album=" + album +
                '}';
    }
}