package com.yy.app.base;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * 公共文本改变事件
 */
public class BaseTextWatcher implements TextWatcher {

    private final static String TAG = "BaseTextWatcher";


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d(TAG, "beforeTextChanged: " + charSequence);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d(TAG, "onTextChanged: " + charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

        Log.d(TAG, "afterTextChanged: " + editable.toString());

    }
}
