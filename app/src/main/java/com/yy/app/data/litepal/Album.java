package com.yy.app.data.litepal;

import android.support.annotation.NonNull;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Album extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String name;

    private float price;

    private byte[] cover;

    private List<Song> songs = new ArrayList<Song>();

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", cover=" + Arrays.toString(cover) +
                ", songs=" + songs +
                '}';
    }
}