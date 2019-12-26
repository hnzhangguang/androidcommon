package com.yy.app.components.materialdesign;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;
import com.yy.app.material.MaterialActivity;

/**
 * 材料设计布局
 * <p>
 * 1, 添加menu
 * 2,
 */
public class MaterialDesignActivity extends BaseActivity {


    Toolbar toolbar;

    @Override
    public void initContentViewXml() {

        setContentView(R.layout.activity_material_design);
    }


    @Override
    public void initView() {
        super.initView();

        // 1, 使用 toolbar
        toolbar = findViewById(R.id.toolbar);
        //设置为使用自己的布局中的toolbar控件
        setSupportActionBar(toolbar);

        // 2, 修改toolbar 的属性
        //修改toolbar的属性
        // 设置正标题
        toolbar.setTitle("正标题");
        // 设置副标题
        toolbar.setSubtitle("副标题");
        // 设置左边按钮图片
        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        // 设置(Log)标题与左边按钮之间图标
        toolbar.setLogo(R.mipmap.ic_launcher);

        // 3, 通过ActionBar来修改属性
        ActionBar supportActionBar = getSupportActionBar();

        // Toolbar点击的回调
        // 点击左侧按钮监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("点击了Navigation按钮");
            }
        });
        // toolbar的Menu回调
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showToast("toolbar的Menu回调:" + item.getTitle());
                return false;
            }
        });

        findViewById(R.id.material_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaterialDesignActivity.this, MaterialActivity.class);
                startActivity(intent);
            }
        });


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
     * 为界面添加 menu 菜单
     * 1, 使用xml 添加menu
     * 2, 使用代码添加menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //1, 使用代码创建menu
        //menu.add(0, 0, 0, "搜索").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, 0, 0, "搜索").setIcon(android.support.v7.appcompat.R.drawable.abc_ic_search_api_material).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        //2, 使用加载xml文件的方式创建
        getMenuInflater().inflate(R.menu.menu, menu);
        //添加子菜单
        menu.addSubMenu(0, 1, 0, "submenu").setIcon(R.mipmap.ic_launcher).addSubMenu(0, 2, 0,
                "submenu1");
        return super.onCreateOptionsMenu(menu);


    }


    /**
     * 为 menu 设置监听事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(this, "点击搜索", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "点击submenu", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "submenu1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "点击设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_edit:
                Toast.makeText(this, "点击编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this, "点击分享", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

