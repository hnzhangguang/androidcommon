package net.designpattern.factory.abstractfactory;

import net.designpattern.factory.beans.BMW;
import net.designpattern.factory.beans.BMWx3;
import net.designpattern.factory.beans.CarActivity;
import net.designpattern.factory.beans.CarActivityA;

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

public class BMWx3ActivityAFactory extends AbstractFactory {
    @Override
    public BMW getCar() {
        return new BMWx3();
    }

    @Override
    public CarActivity getActivity() {
        return new CarActivityA();
    }
}
