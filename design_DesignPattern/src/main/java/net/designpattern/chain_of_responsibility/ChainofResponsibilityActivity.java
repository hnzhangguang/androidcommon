package net.designpattern.chain_of_responsibility;

import android.os.Bundle;

import net.sxkeji.shixindesignpattern.R;

import androidx.appcompat.app.AppCompatActivity;

public class ChainofResponsibilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chainof_responsibility);


    }

    public static void main(String[] args) {
        MyHandler h1 = new MyHandler("h1");
        MyHandler h2 = new MyHandler("h2");
        MyHandler h3 = new MyHandler("h3");

        h1.setHandler(h2);
        h2.setHandler(h3);

        h1.operator();
    }


}
