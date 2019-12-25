package net.designpattern.singleton;


/**
 * 懒汉子模式(双重同步锁)
 */
public class LazyManSingleton3 {

    private static LazyManSingleton3 sin;         ///直接初始化一个实例对象

    private LazyManSingleton3() {         ///private 类型的构造函数，保证其他类对象不能直接 new一个该对象的实例
    }

    public static LazyManSingleton3 getSin() {         ///该类唯一的一个 public 方法
        if (sin == null) {
            synchronized (LazyManSingleton3.class) {
                if (sin == null) {
                    sin = new LazyManSingleton3();
                }
            }
        }
        return sin;
    }
}