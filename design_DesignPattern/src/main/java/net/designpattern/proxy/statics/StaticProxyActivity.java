package net.designpattern.proxy.statics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.sxkeji.shixindesignpattern.R;
import net.designpattern.proxy.BuyHouse;
import net.designpattern.proxy.BuyHouseImpl;

public class StaticProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_proxy);
    }


    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHosue();
        BuyHouseProxy buyHouseProxy = new BuyHouseProxy(buyHouse);
        buyHouseProxy.buyHosue();
    }

}
