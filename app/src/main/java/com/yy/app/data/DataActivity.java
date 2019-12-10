package com.yy.app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.activeandroid.query.Select;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.app.logger.LogUtil;
import com.google.gson.Gson;
import com.yy.app.R;
import com.yy.app.data.bean.Book;
import com.yy.app.data.bean.Category;
import com.yy.app.data.bean.Item;
import com.yy.app.data.litepal.Song;

import org.litepal.LitePal;
import org.litepal.crud.async.CountExecutor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * litepal 步骤: 1,
 */
public class DataActivity extends AppCompatActivity {

    Button btn_litepal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data);

        btn_litepal = findViewById(R.id.btn_litepal);
        btn_litepal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LitePal();
                    }
                });
    }

    /**
     * Gson 的使用
     */
    public void handleGson() {
        // gson 的使用
        String result = "";
        Gson gson = new Gson();
        Book book = gson.fromJson(result, Book.class);
        Log.i("info", book.getTitle() + ":" + book.getPublisher() + ":" + book.getTags());
    }

    /**
     * JSON -> com.alibaba.fastjson.JSON; 的使用
     *
     * @param result
     */
    private void dealData(String result) {
        Book book = JSON.parseObject(result, Book.class);
        List<Book> books = JSON.parseObject(result, new TypeReference<List<Book>>() {
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
        Log.i("info", book.getTitle() + ":" + book.getPublisher() + ":" + book.getTags().size());
    }

    public void ORM() {

        Category cate = new Category();
        cate.name = "OutLook";
        cate.save();
        Item item = new Item();
        item.name = "Lilei";
        item.category = cate;
        item.save();

        Log.i(
                "info",
                new Select().from(Item.class).where("Name=?", "Lilei").orderBy("Name ASC").execute().size()
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
         * public class Album extends LitePalSupport { @Column(unique = true, defaultValue = "unknown")
         * private String name;
         *
         * <p>private float price;
         *
         * <p>private byte[] cover;
         *
         * <p>private List<Song> songs = new ArrayList<Song>();
         *
         * <p>// generated getters and setters. ... }
         *
         * <p>public class Song extends LitePalSupport { @Column(nullable = false) private String name;
         *
         * <p>private int duration; @Column(ignore = true) private String uselessField;
         *
         * <p>private Album album;
         *
         * <p>// generated getters and setters. ... }
         */

        // 3, Then add these models into the mapping list in litepal.xml:
        // <list>
        //    <mapping class="org.litepal.litepalsample.model.Album" />
        //    <mapping class="org.litepal.litepalsample.model.Song" />
        // </list>

        // 4, SQLiteDatabase db = LitePal.getDatabase();
        // Now the tables will be generated automatically with SQLs like this:
        /**
         * CREATE TABLE album ( id integer primary key autoincrement, name text unique default
         * 'unknown', price real, cover blob );
         *
         * <p>CREATE TABLE song ( id integer primary key autoincrement, name text not null, duration
         * integer, album_id integer );
         */

        // 5 Upgrade tables  -> Upgrade tables in LitePal is extremely easy. Just modify your models
        // anyway you want:
        /**
         * public class Album extends LitePalSupport { @Column(unique = true, defaultValue = "unknown")
         * private String name; @Column(ignore = true) private float price;
         *
         * <p>private byte[] cover;
         *
         * <p>private Date releaseDate;
         *
         * <p>private List<Song> songs = new ArrayList<Song>();
         *
         * <p>// generated getters and setters. ... }
         */

        // 6
        // A releaseDate field was added and price field was annotated to ignore. Then increase the
        // version number in litepal.xml:
        //        <!--
        //                Define the version of your database. Each time you want
        //        to upgrade your database, the version tag would helps.
        //        Modify the models you defined in the mapping tag, and just
        //        make the version value plus one, the upgrade of database
        //        will be processed automatically without concern.
        //        For example:
        //    <version value="1" />
        //                -->
        // <version value="2" />

        SQLiteDatabase db = LitePal.getDatabase();

        //       7 , Save data

        //        Album album = new Album();
        //        album.setName("album");
        //        album.setPrice(10.99f);
        //        album.setCover(getCoverImageBytes());
        //        album.save();
        //        Song song1 = new Song();
        //        song1.setName("song1");
        //        song1.setDuration(320);
        //        song1.setAlbum(album);
        //        song1.save();
        //        Song song2 = new Song();
        //        song2.setName("song2");
        //        song2.setDuration(356);
        //        song2.setAlbum(album);
        //        song2.save();

        // 8, Update data
        //        Album albumToUpdate = LitePal.find(Album.class, 1);
        //        albumToUpdate.setPrice(20.99f); // raise the price
        //        albumToUpdate.save();

        // Delete data
        //        LitePal.delete(Song.class, id);
        //        LitePal.deleteAll(Song.class, "duration > ?", "350");

        // Query data
        //        Song song = LitePal.find(Song.class, id);
        List<Song> allSongs = LitePal.findAll(Song.class);
        //        LitePal.deleteAll(Song.class);  // 删除全部数据
        //        LogUtil.e("1:" + allSongs.size());
        for (int i = 0; i < 5; i++) {
            Song song2 = new Song();
            song2.setName("song" + i);
            //            song2.setDuration(356 + i);
            song2.setAlbum(null);
            song2.save();
        }
        allSongs = LitePal.findAll(Song.class);
        //        LogUtil.e("2:" + allSongs.size());
        for (Song song : allSongs) {
            //            if (song.getName().equals("song1")) {
            //                //song.save();       // 是个异步操作
            //            }
        }
        SystemClock.sleep(2000);
        allSongs = LitePal.findAll(Song.class);
        for (Song allSong : allSongs) {
            LogUtil.e("-> " + allSong);
        }

        try {

            // 执行自己的sql !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Cursor sql = LitePal.findBySQL("sql");
            LitePal.select("name", "author", "pages")
                    .where(" pages > ? ", "400")
                    .order("pages")
                    .limit(5)
                    .offset(2)
                    .find(Song.class);

            // 获取总数量
            int count = LitePal.count(Song.class);
            final CountExecutor countExecutor = LitePal.countAsync(Song.class);

            // 获取某个字段的平均值
            double average = LitePal.average(Song.class, "age");

            int delete = LitePal.delete(Song.class, 1); // 删除id为1的数据
            int i = LitePal.deleteAll(Song.class, "age=55");

            // 更新
            int update = LitePal.update(Song.class, new ContentValues(), 2); //

            // 把集合数据一块儿保存
            LitePal.saveAll(allSongs);

            // 判断是否存在该条件的数据
            boolean exist = LitePal.isExist(Song.class, "age = 90");

            //        List<Song> songs = LitePal.where("name like ? and duration < ?", "song%",
            // "200").order(
            //                "duration").find(Song.class);

        } catch (Exception e) {
            LogUtil.e(e);
        }
    }

    ////////////////////////////////// FileOutputStream and FileInputStream
    // //////////////////////////////////////////////////////

    /**
     * 使用FileOutputStream 文件流的形式 保存
     *
     * @param inputText
     */
    public void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加载保存的文件流 信息
     *
     * @return
     */
    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
