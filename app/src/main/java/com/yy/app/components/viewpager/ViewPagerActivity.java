package com.yy.app.components.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends BaseActivity {


    ViewPager mViewpager;
    private View view1, view2, view3;
    private List<View> viewList;//view数组

    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_view_pager2);
    }

    @Override
    public void initView() {
        super.initView();
        mViewpager = findViewById(R.id.mViewpager);

        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        // 第一种方式
        mViewpager.setAdapter(pagerAdapter);


        // 第二种方式
        // 临时数据
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("temp data:" + i);
        }
        // 设置adapter
        mViewpager.setAdapter(new MyPagerAdapter(this, list));


    }


    /**
     * PageAdapter 必须重写的四个函数：
     * 1, boolean isViewFromObject(View arg0, Object arg1)
     * 2, int getCount()
     * 3, void destroyItem(ViewGroup container, int position,Object object) ->
     * destroyItem（）：从当前container中删除指定位置（position）的View;
     * 4, Object instantiateItem(ViewGroup container, int position)->
     * instantiateItem()：做了两件事，第一：将当前视图添加到container中，第二：返回当前View
     */
    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    };


    @Override
    public void initListener() {
        super.initListener();
    }


    @Override
    public void initData() {
        super.initData();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * ViewPager Adapter
     */
    class MyPagerAdapter extends PagerAdapter {
        private Context mContext;
        private List<String> mData;

        public MyPagerAdapter(Context context, List<String> list) {
            mContext = context;
            mData = list;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(mContext, R.layout.viewpager_item_base, null);
            TextView tv = (TextView) view.findViewById(R.id.viewpager_item_tv);
            tv.setText(mData.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container,position,object); 这一句要删除，否则报错
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


}
