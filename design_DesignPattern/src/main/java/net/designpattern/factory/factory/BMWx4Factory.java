package net.designpattern.factory.factory;

import net.designpattern.factory.beans.BMW;
import net.designpattern.factory.beans.BMWx4;

/**
 * Description:
 * <br> 专门创建宝马 x4 的工厂
 * <p>
 * <br> Created by shixinzhang on 17/9/2.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <br> https://about.me/shixinzhang
 */

public class BMWx4Factory extends Factory {
    @Override
    public BMW createCar() {
        return new BMWx4();
    }
}
