package com.yy.app.components;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yy.app.R;


/**
 * 目的:
 * 减少布局层次结构.
 * <p>
 * <p>
 * 主要学习约束布局的使用:
 * app:layout_constraintLeft_toLeftOf="parent"
 * <p>
 * 当我们希望控件A与控件B左侧对齐时，就可以使用该属性。
 * app:layout_constraintLeft_toLeftOf="@id/viewB"
 * 与之类似的还有几个属性：
 * <p>
 * layout_constraintRight_toLeftOf
 * layout_constraintRight_toRightOf
 * layout_constraintTop_toTopOf
 * layout_constraintTop_toBottomOf
 * layout_constraintBottom_toTopOf
 * layout_constraintBottom_toBottomOf
 * layout_constraintBaseline_toBaselineOf
 */
public class ConstraintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
    }
}
