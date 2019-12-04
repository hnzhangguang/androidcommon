package com.yy.app.components;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class ViewPagerActivity extends BaseActivity {

    ViewPager mViewPager;


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_view_pager);
    }

    @Override
    public void initView() {
        super.initView();
        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);


    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        super.initData();

        // 临时数据
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("temp data:" + i);
        }
        // 设置adapter
        mViewPager.setAdapter(new MyPagerAdapter(this, list));


    }


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



