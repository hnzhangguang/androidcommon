package net.designpattern.factory;

import net.designpattern.factory.factory.BMWx3Factory;
import net.designpattern.factory.factory.BMWx4Factory;
import net.designpattern.factory.factory.Factory;

import org.junit.Test;

/**
 * Description:
 * <br> 工厂方法模式的测试
 * <p>
 * <br> Created by shixinzhang on 17/9/2.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <br> https://about.me/shixinzhang
 */

public class FactoryTest {
    @Test
    public void testFactory() {
        Factory bmwFactory = new BMWx3Factory();
        bmwFactory.createCar();

        bmwFactory = new BMWx4Factory();
        bmwFactory.createCar();
    }
}
