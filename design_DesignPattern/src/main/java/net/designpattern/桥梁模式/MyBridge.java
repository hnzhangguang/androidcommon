package net.designpattern.桥梁模式;


/**
 *
 */
public class MyBridge extends Bridge {
    public void method() {
        getSource().method();
    }
}  