package com.yy.app.activity.fragment;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;

import java.util.List;


/**
 * 学习fragment的使用方式:
 * 1, add commit()
 * 2, replace() commit()
 * 3, show() hide() 方式
 * 4, addToBackStack(null) -> 按返回键的时候回到此fragment ;  popBackStack(); -> 回到activity
 */
public class FragmentActivity extends BaseActivity implements InterfaceFragment,
        Fragment2.OnFragmentInteractionListener {

    // 方式1
    Button btn_fragment2;
    Button btn_fragment1;
    // 方式2
    Button btn_fragment3;
    Button btn_fragment4;
    //方式3
    Button btn_fragment5;
    Button btn_fragment6;
    FragmentManager supportFragmentManager;


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_fragment);
        supportFragmentManager = getSupportFragmentManager();
    }

    @Override
    public void initView() {
        super.initView();
        btn_fragment1 = findViewById(R.id.btn_fragment1);
        btn_fragment2 = findViewById(R.id.btn_fragment2);
        btn_fragment3 = findViewById(R.id.btn_fragment3);
        btn_fragment4 = findViewById(R.id.btn_fragment4);
        btn_fragment5 = findViewById(R.id.btn_fragment5);
        btn_fragment6 = findViewById(R.id.btn_fragment6);
    }


    @Override
    public void initListener() {
        super.initListener();

        // 常规使用方法,add(),  commit()
        btn_fragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

                // 先获取到所有的fragment实例,然后移除掉, 否则会持续累加
                List<Fragment> fragments = supportFragmentManager.getFragments();
                for (Fragment fragment : fragments) {
                    fragmentTransaction.remove(fragment);
                }

                fragmentTransaction.add(R.id.fragment_container, new Fragment1());
                fragmentTransaction.commit();
            }
        });
        btn_fragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

                // 先获取到所有的fragment实例,然后移除掉,否则会持续累加
                List<Fragment> fragments = supportFragmentManager.getFragments();
                for (Fragment fragment : fragments) {
                    fragmentTransaction.remove(fragment);
                }
                fragmentTransaction.add(R.id.fragment_container, new Fragment2());
                fragmentTransaction.commit();
            }
        });
        // replace() commit();
        btn_fragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Fragment1());
                fragmentTransaction.addToBackStack(null);  // 添加到返回栈中,从下一个fragment可以回退到这个fragment中
                fragmentTransaction.commit();
            }
        });
        btn_fragment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Fragment2());
                fragmentTransaction.addToBackStack(null);  // 添加到返回栈中,从下一个fragment可以回退到这个fragment中
                fragmentTransaction.commit();
            }
        });
        btn_fragment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == firstStepFragment) {
                    firstStepFragment = new Fragment1();
                }
                switchFragment(firstStepFragment);
            }
        });
        btn_fragment6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == secondStepFragment) {
                    secondStepFragment = new Fragment2();
                }
                switchFragment(secondStepFragment);
            }
        });
    }

    Fragment1 firstStepFragment = null;
    Fragment2 secondStepFragment = null;

    /**
     * show hide 的方式使用fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        List<Fragment> fragments = fm.getFragments();
        boolean isFound = false;
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragm = fragments.get(i);
            // 找到让其显示,否则隐藏
            if (fragment.equals(fragm)) {
                ft.show(fragm).commit();
                isFound = true;
            } else {
                ft.hide(fragm);
            }
        }
        // 如果在已有fragment实例中没有找到要显示的fragment,就添加并显示
        if (!isFound) {
            ft.add(R.id.fragment_container, fragment).commit();
        }
    }


    @Override
    public void initData() {
        super.initData();

        // 2, Activity 也可使用 findFragmentById() 或 findFragmentByTag()，通过从 FragmentManager 获取对 Fragment
        // 的引用来调用片段中的方法。例如：
        Fragment1 fragment =
                (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment_container);


    }

    @Override
    public void callBackFragment(Fragment fragment) {
        // 当activity满足一定条件的时候,更新对应的fragment对象
    }


    /**
     * fragment2 的接口实现
     *
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
