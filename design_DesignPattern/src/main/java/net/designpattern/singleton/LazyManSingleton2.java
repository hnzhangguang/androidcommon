package net.designpattern.singleton;


/**
 * 第二种 懒汉子模式(懒汉式同步方法实现多线程同步问题(效率低))
 */
class LazyManSingleton2 {

    private static LazyManSingleton2 instance = null;

    private LazyManSingleton2() {
    }

    /**
     * 方式一
     *
     * @return
     */
    public synchronized static LazyManSingleton2 getInstance() {
        try {
            if (instance != null) {//懒汉式

            } else {
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(300);
                instance = new LazyManSingleton2();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 方式二
     *
     * @return
     */
    public static LazyManSingleton2 getSin() {         ///该类唯一的一个 public 方法
        if (instance == null) {
            synchronized (LazyManSingleton3.class) {
                if (instance == null) {
                    instance = new LazyManSingleton2();
                }
            }
        }
        return instance;
    }
}