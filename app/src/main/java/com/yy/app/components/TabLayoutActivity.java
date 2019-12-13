package com.yy.app.components;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.yy.app.R;
import com.yy.app.activity.fragment.Fragment2;
import com.yy.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * TabLayout+ViewPager+Fragment的简单用法总结。
 * 所使用的两种PagerAdapter的差别分析及选择。
 * 懒加载策略。
 * 卡顿及性能优化建议。
 * <p>
 * /////
 * <p>
 * 创建存储多个Fragment实例的列表
 * 创建PagerAdapter实例并关联到Viewpager中
 * 将ViewPager关联到Tablayout中
 * 根据需求改写Tablayout属性*
 * <p>
 * //
 */
public class TabLayoutActivity extends BaseActivity implements Fragment2.OnFragmentInteractionListener {


    TabLayout tabLayout;
    ViewPager viewPager;

    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_tab_layout);
    }

    @Override
    public void initView() {
        super.initView();

        tabLayout = findViewById(R.id.tl_tabs);
        viewPager = findViewById(R.id.vp_content);

        fragments.add(Fragment2.newInstance("11111", "11111"));
        fragments.add(Fragment2.newInstance("22222", "22222"));
        fragments.add(Fragment2.newInstance("33333", "33333"));
        fragments.add(Fragment2.newInstance("44444", "44444"));
        fragments.add(Fragment2.newInstance("55555", "55555"));
        titles.add("fragment1");
        titles.add("fragment2");
        titles.add("fragment3");
        titles.add("fragment4");
        titles.add("fragment5");
        // 设置adapter
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return titles.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
