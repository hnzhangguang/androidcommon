package com.yy.app.contentprovider;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.List;

/**
 * 1, 查询通讯录信息(getContentResolver().query() 方法) 2,
 */
public class ContentProviderActivity extends BaseActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_content_provider);
    }

    @Override
    public void initView() {
        super.initView();

        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        // 创建数据库
        dbHelper.getWritableDatabase();
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        super.initData();
    }

    /**
     * 操作数据库
     */
    public void hander() {

        // add
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // 开始组装第一条数据
        values.put("name", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
        db.insert("Book", null, values); // 插入第一条数据
        values.clear();
        // 开始组装第二条数据
        values.put("name", "The Lost Symbol");
        values.put("author", "Dan Brown");
        values.put("pages", 510);
        values.put("price", 19.95);
        db.insert("Book", null, values); // 插入第二条数据

        // updata

        ContentValues updatevalues = new ContentValues();
        updatevalues.put("price", 10.99);
        db.update("Book", updatevalues, "name = ?", new String[]{"The Da Vinci Code"});

        // 删除

        SQLiteDatabase db2 = dbHelper.getWritableDatabase();
        db2.delete("Book", "pages > ?", new String[]{"500"});

        // query

        db = dbHelper.getWritableDatabase();
        // 查询Book表中所有的数据
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                // 遍历Cursor对象，取出数据并打印
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d("MainActivity", "book name is " + name);
                Log.d("MainActivity", "book author is " + author);
                Log.d("MainActivity", "book pages is " + pages);
                Log.d("MainActivity", "book price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    List<String> contactsList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    /**
     * 查询通讯录信息
     */
    public void queryContactInfos() {

        ListView contactsView = null;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }
    }

    // 查询通讯录信息
    private void readContacts() {
        Cursor cursor = null;
        try {
            // 查询联系人数据
            cursor =
                    getContentResolver()
                            .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                                    null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // 获取联系人姓名
                    String displayName =
                            cursor.getString(
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    // 获取联系人手机号
                    String number =
                            cursor.getString(
                                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName + "\n" + number);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
