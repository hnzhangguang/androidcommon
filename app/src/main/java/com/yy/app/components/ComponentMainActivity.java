package com.yy.app.components;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.yy.app.R;
import com.yy.app.activity.fragment.FragmentActivity;
import com.yy.app.animator.ValueAnimatorActivity;
import com.yy.app.base.BaseActivity;
import com.yy.app.components.bubble.BubbleActivity;
import com.yy.app.components.dialog.DialogActivity;
import com.yy.app.components.listview.swipemenulistview.SwipeMenuListViewActivity;
import com.yy.app.components.swiperefreshlayout.SwipeRefreshLayoutActivity;
import com.yy.app.components.viewpager.FragmentViewPagerActivity;
import com.yy.app.components.viewpager.ViewPager2Activity;
import com.yy.app.components.viewpager.ViewPagerActivity;


/**
 * 控件学习主界面
 */
public class ComponentMainActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    Button btn_fragment = null;
    Button btn_bubble = null;
    Button btn_SwipeRefreshLayout = null;
    Button btn_other = null;
    Button btn_Animator = null;
    Button btn_ViewPager = null;
    Button btn_tablayout = null;
    Button btn_swiplistview = null;
    Button btn_dialog = null;
    Spinner spinner = null;

    BaseActivity mActivity = null;

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_component_main);
    }

    @Override
    public void initView() {
        super.initView();

        mActivity = this;

        btn_swiplistview = findViewById(R.id.btn_swiplistview);
        btn_fragment = findViewById(R.id.btn_fragment);
        btn_ViewPager = findViewById(R.id.btn_viewpager);
        btn_bubble = findViewById(R.id.btn_bubble);
        btn_other = findViewById(R.id.btn_other);
        btn_Animator = findViewById(R.id.btn_Animator);
        btn_SwipeRefreshLayout = findViewById(R.id.btn_SwipeRefreshLayout);
        btn_tablayout = findViewById(R.id.btn_tablayout);
        btn_dialog = findViewById(R.id.btn_dialog);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    @Override
    public void initListener() {
        super.initListener();


        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this,
                        DialogActivity.class);
                startActivity(intent);
            }
        });
        btn_swiplistview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this,
                        SwipeMenuListViewActivity.class);
                startActivity(intent);
            }
        });
        btn_tablayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, TabLayoutActivity.class);
                startActivity(intent);
            }
        });

        btn_ViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, ViewPager2Activity.class);
                intent = new Intent(ComponentMainActivity.this, ViewPagerActivity.class);
                intent = new Intent(ComponentMainActivity.this, FragmentViewPagerActivity.class);
                startActivity(intent);
            }
        });

        btn_Animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, ValueAnimatorActivity.class);
                startActivity(intent);
            }
        });

        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });
        btn_SwipeRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, SwipeRefreshLayoutActivity.class);
                startActivity(intent);
            }
        });
        btn_bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ComponentMainActivity.this, BubbleActivity.class);
                startActivity(intent);

            }
        });
        btn_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComponentMainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        //        添加监听
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
