package net.designpattern.singleton;


import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * ﻿    5,序列化与反序列化的单例模式实现(基于静态内置类实现)
 */
public class StaticInnerSingleton2 implements Serializable {


    private static final long serialVersionUID = 1L;

    //内部类  
    private static class MySingletonHandler {
        private static StaticInnerSingleton2 instance = new StaticInnerSingleton2();
    }

    private StaticInnerSingleton2() {
    }

    public static StaticInnerSingleton2 getInstance() {
        return MySingletonHandler.instance;
    }

    //该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉
    protected Object readResolve() throws ObjectStreamException {
        System.out.println("调用了readResolve方法！");
        return MySingletonHandler.instance;
    }
}