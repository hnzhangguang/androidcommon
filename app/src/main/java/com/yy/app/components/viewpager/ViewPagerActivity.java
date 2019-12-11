package com.yy.app.components.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.List;

/**
 * 1, PagerAdapter 的常用有:  FragmentPagerAdapter , FragmentStatePagerAdapter
 * 1.2 PagerAdapter 的4个实现方法(isViewFromObject(),getCount(),destroyItem(),instantiateItem())
 * 2, PagerTabStrip与PagerTitleStrip
 */
public class ViewPagerActivity extends BaseActivity {


    ViewPager mViewpager;
    PagerTitleStrip pagertitle;
    PagerTabStrip pagerTabStrip;
    private View view1, view2, view3;
    private List<View> viewList;//view数组
    private List<String> titleList;


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_view_pager2);
    }

    @Override
    public void initView() {
        super.initView();
        mViewpager = findViewById(R.id.mViewpager);
        //        pagertitle = findViewById(R.id.pagertitle);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
        pagerTabStrip.setTabIndicatorColorResource(R.color.green);  //更改下划线颜色

        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        titleList = new ArrayList<String>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        titleList.add("我是白色");
        titleList.add("我是黄色");
        titleList.add("我是紫色");

        // 第一种方式
        mViewpager.setAdapter(pagerAdapter);


        // 第二种方式
        // 临时数据
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("temp data:" + i);
        }
        // 设置adapter
        //        mViewpager.setAdapter(new MyPagerAdapter(this, list));


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

        // 删除view
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        // 添加view
        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @NonNull
        @Override
        public CharSequence getPageTitle(int position) {
            //            return titleList.get(position);    // 1, 简单实现title


            // 2, 自定义title
            SpannableStringBuilder ssb = new SpannableStringBuilder(" " + titleList.get(position)); //
            // space added before text
            // for
            Drawable myDrawable = getResources().getDrawable(R.drawable.icon_toolbar);
            myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(),
                    myDrawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(myDrawable,
                    ImageSpan.ALIGN_BASELINE);
            ForegroundColorSpan fcs = new ForegroundColorSpan(Color.GREEN);// 字体颜色设置为绿色
            ssb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置图标
            ssb.setSpan(fcs, 1, ssb.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置字体颜色
            ssb.setSpan(new RelativeSizeSpan(1.2f), 1, ssb.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ssb;


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
