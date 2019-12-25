package net.designpattern.singleton;

/**
 * 第二种  懒汉子模式
 */

public class LazyManSingleton {


    private static LazyManSingleton sin;         ///直接初始化一个实例对象

    private LazyManSingleton() {         ///private 类型的构造函数，保证其他类对象不能直接 new一个该对象的实例
    }

    public static LazyManSingleton getSin() {         ///该类唯一的一个 public 方法
        if (sin == null) {
            sin = new LazyManSingleton();
        }
        return sin;
    }


}
