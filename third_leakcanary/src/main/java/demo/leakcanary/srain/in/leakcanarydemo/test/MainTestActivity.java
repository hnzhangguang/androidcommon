package demo.leakcanary.srain.in.leakcanarydemo.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import demo.leakcanary.srain.in.leakcanarydemo.R;

public class MainTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        test();
                    }
                });
            }
        });


    }


    public void test() {
        Log.e("mmmm", "mmm");
        Intent intent = new Intent(MainTestActivity.this, MainTest2Activity.class);
        startActivity(intent);
    }


}
