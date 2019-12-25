package net.designpattern.adapter.classadapter;


/**
 * test 类
 */
public class AdapterTest {

    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }


//    输出：
//            this is original method!
//            this is the targetable method!


} 