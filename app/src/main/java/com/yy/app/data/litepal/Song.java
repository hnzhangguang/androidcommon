package com.yy.app.data.litepal;

import android.graphics.drawable.InsetDrawable;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Song extends LitePalSupport {

    @Column(nullable = false)
    private String name;


    private int duration;

    @Column(ignore = true)
    private String uselessField;

    private Album album;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
                ", duration=" + duration +
                ", uselessField='" + uselessField + '\'' +
                ", album=" + album +
                '}';
    }
}