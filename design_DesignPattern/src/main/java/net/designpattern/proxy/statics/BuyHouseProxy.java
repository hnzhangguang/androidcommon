package net.designpattern.proxy.statics;

import net.designpattern.proxy.BuyHouse;

/**
 * 说明:
 * <p>
 * 作者 zhangg
 * 时间: 2019/1/13.
 */

public class BuyHouseProxy implements BuyHouse {


    private BuyHouse buyHouse;

    public BuyHouseProxy(final BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }


    @Override
    public void buyHosue() {


        System.out.println("买房前准备");
        buyHouse.buyHosue();
        System.out.println("买房后装修");


    }
}
