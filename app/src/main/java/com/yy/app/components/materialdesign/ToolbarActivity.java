package com.yy.app.components.materialdesign;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;


/**
 * Toolbar 的常规操作
 */
public class ToolbarActivity extends BaseActivity {

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_toolbar);
    }

    @Override
    public void initView() {
        super.initView();
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // 然后可以做其他的操作
        ActionBar supportActionBar = getSupportActionBar();

        //启用 "向上" 按钮
        // Enable the Up button
        supportActionBar.setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public void initListener() {
        super.initListener();


        // 其他可操作的API 有:

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
        @SuppressLint("RestrictedApi")
        boolean b = supportActionBar.closeOptionsMenu();  //
        float elevation = supportActionBar.getElevation();
        CharSequence title = supportActionBar.getTitle();
        supportActionBar.setHomeButtonEnabled(true);
//        supportActionBar.setHomeAsUpIndicator(1);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        // 获取menu里面的元素
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) searchItem.getActionView();

        return super.onCreateOptionsMenu(menu);
    }


}
