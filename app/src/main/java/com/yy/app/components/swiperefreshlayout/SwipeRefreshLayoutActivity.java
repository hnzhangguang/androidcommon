package com.yy.app.components.swiperefreshlayout;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import java.util.ArrayList;


/**
 * 滑动刷新 SwipeRefreshLayout
 * <p>
 * <p>
 * swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
 *
 * @Override public void onRefresh() {
 * //这里获取数据的逻辑
 * swipeRefreshLayout.setRefreshing(false);
 * }
 * });
 * <p>
 * //设置进度View样式的大小，只有两个值DEFAULT和LARGE，表示默认和较大
 * swipeRefreshLayout.setSize(DEFAULT);
 * //设置触发下拉刷新的距离
 * swipeRefreshLayout.setDistanceToTriggerSync(300);
 * //设置动画样式下拉的起始点和结束点，scale 是指设置是否需要放大或者缩小动画。
 * swipeRefreshLayout.setProgressViewOffset(boolean scale, int start, int end)
 * //设置动画样式下拉的结束点，scale 是指设置是否需要放大或者缩小动画
 * swipeRefreshLayout.setProgressViewEndTarget(boolean scale, int end);
 * //如果自定义了swipeRefreshLayout，可以通过这个回调方法决定是否可以滑动。
 * setOnChildScrollUpCallback(@Nullable OnChildScrollUpCallback callback)
 */
public class SwipeRefreshLayoutActivity extends BaseActivity {


    ListView listView;
    SwipeRefreshLayout swipe;
    MyAdapter adapter;
    ArrayList<String> array = new ArrayList<>();
    ArrayList<String> datalist = new ArrayList<>();
    int index = 20; //最开始加载的数据
    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                adapter.notifyDataSetChanged();
                swipe.setRefreshing(false);//刷新完毕 ，图标消失
            }
        }
    };


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_swipe_refresh_layout);
    }

    @Override
    public void initView() {
        super.initView();
        init();
    }


    private void init() {
        for (int i = 0; i < 100; i++) {
            array.add("我是数据：" + i);

        }
        for (int i = 0; i < 20; i++) {
            datalist.add(array.get(i));
        }
        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setSize(SwipeRefreshLayout.DEFAULT);//设置加载默认图标
        // 下拉刷新触发事件
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int indexMax = index + 20;
                for (int i = index; i < indexMax; i++) {
                    datalist.add(array.get(i));
                }
                index = indexMax;
                hand.sendEmptyMessageDelayed(1, 3000);
            }
        });
        if (Build.VERSION.SDK_INT >= 23) {
            swipe.setProgressBackgroundColorSchemeColor(
                    getResources().getColor(android.R.color.holo_orange_light, getTheme()));
            swipe.setColorSchemeColors(//刷新控件动画中的颜色
                    getResources().getColor(android.R.color.holo_blue_dark, getTheme()),
                    getResources().getColor(android.R.color.holo_red_dark, getTheme()),
                    getResources().getColor(android.R.color.holo_green_dark, getTheme())
            );
        }
        listView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int i) {
            return datalist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(SwipeRefreshLayoutActivity.this);
            textView.setTextSize(22);
            textView.setTextColor(viewGroup.getContext().getResources().getColor(android.R.color.holo_green_dark,
                    //getTheme()获取当前Activity主题
                    viewGroup.getContext().getTheme()));
            textView.setText(datalist.get(i));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast(((TextView) v).getText().toString());
                }
            });
            return textView;

        }
    }


}


