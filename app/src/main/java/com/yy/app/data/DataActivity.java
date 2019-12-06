package com.yy.app.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.activeandroid.query.Select;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.yy.app.R;
import com.yy.app.data.bean.Book;
import com.yy.app.data.bean.Category;
import com.yy.app.data.bean.Item;
import com.yy.app.data.litepal.Album;
import com.yy.app.data.litepal.Song;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data);


    }

    /**
     * Gson 的使用
     */
    public void handleGson() {
        // gson 的使用
        String result = "";
        Gson gson = new Gson();
        Book book = gson.fromJson(result, Book.class);
        Log.i("info", book.getTitle() + ":" + book.getPublisher() + ":"
                + book.getTags());
    }

    /**
     * JSON -> com.alibaba.fastjson.JSON; 的使用
     *
     * @param result
     */
    private void dealData(String result) {
        Book book = JSON.parseObject(result, Book.class);
        List<Book> books = JSON.parseObject(result,
                new TypeReference<List<Book>>() {
                });
        Book book1 = new Book();
        book1.setTitle("biaoti");
        Book book2 = new Book();
        book2.setTitle("biaoti");
        Book book3 = new Book();
        book2.setTitle("biaoti");

        List<Book> list = new ArrayList<Book>();
        list.add(book1);
        list.add(book2);
        list.add(book3);
        JSON.toJSON(list);
        Log.i("info", book.getTitle() + ":" + book.getPublisher() + ":"
                + book.getTags().size());
    }


    public void ORM() {

        Category cate = new Category();
        cate.name = "OutLook";
        cate.save();
        Item item = new Item();
        item.name = "Lilei";
        item.category = cate;
        item.save();

        Log.i("info", new Select().from(Item.class).where("Name=?", "Lilei")
                .orderBy("Name ASC").execute().size()
                + "");


        // 加载有条件的数据
        // Item item2 = Item.load(Item.class, 1);
        // 删除
        // item2.delete();
        // 条件删除
        // new Delete().from(Item.class).where("Name=?", "Lilei").execute();


    }


    public void LitePal() {


        // 1, LitePal.initialize(this);
        // 2
        /**
         * public class Album extends LitePalSupport {
         *
         *     @Column(unique = true, defaultValue = "unknown")
         *     private String name;
         *
         *     private float price;
         *
         *     private byte[] cover;
         *
         *     private List<Song> songs = new ArrayList<Song>();
         *
         *     // generated getters and setters.
         *     ...
         * }
         *
         * public class Song extends LitePalSupport {
         *
         *     @Column(nullable = false)
         *     private String name;
         *
         *     private int duration;
         *
         *     @Column(ignore = true)
         *     private String uselessField;
         *
         *     private Album album;
         *
         *     // generated getters and setters.
         *     ...
         * }
         *
         */

        // 3, Then add these models into the mapping list in litepal.xml:
        // <list>
        //    <mapping class="org.litepal.litepalsample.model.Album" />
        //    <mapping class="org.litepal.litepalsample.model.Song" />
        //</list>

        //4, SQLiteDatabase db = LitePal.getDatabase();
        // Now the tables will be generated automatically with SQLs like this:
        /**
         *CREATE TABLE album (
         * 	id integer primary key autoincrement,
         * 	name text unique default 'unknown',
         * 	price real,
         * 	cover blob
         * );
         *
         * CREATE TABLE song (
         * 	id integer primary key autoincrement,
         * 	name text not null,
         * 	duration integer,
         * 	album_id integer
         * );
         */

        // 5 Upgrade tables  -> Upgrade tables in LitePal is extremely easy. Just modify your models anyway you want:
        /**
         * public class Album extends LitePalSupport {
         *
         *     @Column(unique = true, defaultValue = "unknown")
         *     private String name;
         *
         *     @Column(ignore = true)
         *     private float price;
         *
         *     private byte[] cover;
         *
         *     private Date releaseDate;
         *
         *     private List<Song> songs = new ArrayList<Song>();
         *
         *     // generated getters and setters.
         *     ...
         * }
         */

        // 6
        //A releaseDate field was added and price field was annotated to ignore. Then increase the version number in litepal.xml:
//        <!--
//                Define the version of your database. Each time you want
//        to upgrade your database, the version tag would helps.
//        Modify the models you defined in the mapping tag, and just
//        make the version value plus one, the upgrade of database
//        will be processed automatically without concern.
//        For example:
//    <version value="1" />
//                -->
        //<version value="2" />


        //       7 , Save data

        Album album = new Album();
        album.setName("album");
        album.setPrice(10.99f);
        album.setCover(getCoverImageBytes());
        album.save();
        Song song1 = new Song();
        song1.setName("song1");
        song1.setDuration(320);
        song1.setAlbum(album);
        song1.save();
        Song song2 = new Song();
        song2.setName("song2");
        song2.setDuration(356);
        song2.setAlbum(album);
        song2.save();

        // 8, Update data
        Album albumToUpdate = LitePal.find(Album.class, 1);
        albumToUpdate.setPrice(20.99f); // raise the price
        albumToUpdate.save();


        // Delete data
//        LitePal.delete(Song.class, id);
        LitePal.deleteAll(Song.class, "duration > ?", "350");


        //Query data
//        Song song = LitePal.find(Song.class, id);
        List<Song> allSongs = LitePal.findAll(Song.class);
        List<Song> songs = LitePal.where("name like ? and duration < ?", "song%", "200").order("duration").find(Song.class);

    }

    private byte[] getCoverImageBytes() {

        return null;
    }


}
