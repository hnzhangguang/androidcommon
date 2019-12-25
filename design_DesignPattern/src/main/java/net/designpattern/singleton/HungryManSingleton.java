package net.designpattern.singleton;

/**
 * 第一种 饿汉子模式
 */

public class HungryManSingleton {

    private static HungryManSingleton sin = new HungryManSingleton();         ///直接初始化一个实例对象

    private HungryManSingleton() {         ///private 类型的构造函数，保证其他类对象不能直接 new一个该对象的实例
    }

    public static HungryManSingleton getSin() {         ///该类唯一的一个 public 方法
        return sin;
    }


}
