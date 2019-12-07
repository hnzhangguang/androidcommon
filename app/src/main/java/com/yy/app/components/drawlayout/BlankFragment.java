package com.yy.app.components.drawlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.logger.LogUtil;
import com.yy.app.R;

/**
 * 1,  fragment 的生命周期,
 * 2, set get  : getArguments()  -> bundle 对象
 */
public class BlankFragment extends Fragment {


    private TextView textView;

    public BlankFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        LogUtil.w("1, BlankFragment -> onAttach ");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.w("2, BlankFragment -> onCreate ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.e("3, BlankFragment -> onCreateView ");

        View root = inflater.inflate(R.layout.fragment_blank, container, false);
        textView = root.findViewById(R.id.tv_textView);
        String key = getArguments().getString("key");
        textView.setText(key);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.e("4, BlankFragment -> onActivityCreated ");
    }


    @Override
    public void onDetach() {


        LogUtil.e("BlankFragment -> onDetach ");
        super.onDetach();
    }


}
