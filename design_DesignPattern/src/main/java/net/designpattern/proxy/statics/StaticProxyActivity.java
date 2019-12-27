package net.designpattern.proxy.statics;

import android.os.Bundle;

import net.designpattern.proxy.BuyHouse;
import net.designpattern.proxy.BuyHouseImpl;
import net.sxkeji.shixindesignpattern.R;

import androidx.appcompat.app.AppCompatActivity;

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
