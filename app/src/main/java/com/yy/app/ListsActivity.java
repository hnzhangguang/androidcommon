package com.yy.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

/**
 * ListActivity 的使用
 */
public class ListsActivity extends ListActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_lists);


        //1.数据源
        String[] data = {"老师", "学生", "课桌", "书本", "铅笔", "橡皮", "粉笔", "黑板", "凳子", "扫帚", "簸箕", "炉子", "窗花", "讲台", "教鞭", "小红花", "花瓶"};
        //2.适配器  
        ArrayAdapter
                arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        //3.绑定  
        setListAdapter(arrayAdapter);


    }


}
