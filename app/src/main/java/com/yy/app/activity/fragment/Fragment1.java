package com.yy.app.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.app.R;


/**
 * Fragment 的使用
 * Fragment 的生命周期
 */
public class Fragment1 extends Fragment {
    private static String ARG_PARAM = "param_key";
    private Activity mActivity;


    // 回调 -> 和外界通信
    InterfaceFragment callback;


    public void setCallback(InterfaceFragment callback) {
        this.callback = callback;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 加载布局
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 加载布局资源
        View root = inflater.inflate(R.layout.fragment_1, container, false);
        TextView view = root.findViewById(R.id.fragment_text);
        view.setText("param");
        mActivity = getActivity();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        // 1, 可通过 getActivity() 访问 FragmentActivity 实例，并轻松执行在 Activity 布局中查找视图等任务：
        LinearLayout linearLayout = getActivity().findViewById(R.id.fragment_container);

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 实例化
     *
     * @param str
     * @return
     */
    public static Fragment1 newInstance(String str) {
        Fragment1 fragment = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        fragment.setArguments(bundle);   //设置参数
        return fragment;
    }
}