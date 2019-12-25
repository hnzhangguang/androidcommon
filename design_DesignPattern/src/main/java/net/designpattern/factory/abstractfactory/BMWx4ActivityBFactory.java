package net.designpattern.factory.abstractfactory;

import net.designpattern.factory.beans.BMW;
import net.designpattern.factory.beans.BMWx4;
import net.designpattern.factory.beans.CarActivity;
import net.designpattern.factory.beans.CarActivityB;

/**
 * Description:
 * <br>
 * <p>
 * <br> Created by shixinzhang on 17/9/2.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <br> https://about.me/shixinzhang
 */

public class BMWx4ActivityBFactory extends AbstractFactory {
    @Override
    public BMW getCar() {
        return new BMWx4();
    }

    @Override
    public CarActivity getActivity() {
        return new CarActivityB();
    }
}
