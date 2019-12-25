package net.designpattern.adapter.objectadapter;


/**
 * 要适配成的接口(目标)
 */
public interface Targetable {

    /* 与原类中的方法相同 */
    public void method1();

    /* 新类的方法 */
    public void method2();
} 