package function.etc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.yy.app.R;

public class LoadLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_load_layout);


        // 方式一
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate
                (R.layout.layout1, null);
        LinearLayout layout2 = (LinearLayout) LayoutInflater.from(LoadLayoutActivity.this)
                .inflate(R.layout.layout2, null);


        // 方式二
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout3 = (LinearLayout) layoutInflater.inflate(R.layout
                .layout1, null);


        // 方式三
        LinearLayout layout4 = (LinearLayout) getLayoutInflater().inflate(R.layout
                .layout2, null);
        LinearLayout layout5 = (LinearLayout) LoadLayoutActivity.this.getLayoutInflater().inflate
                (R.layout.layout1, null);


    }
}
