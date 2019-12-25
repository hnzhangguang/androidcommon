package net.designpattern.桥梁模式;


/**
 * mysql 连接数据库
 */
public class SourceSub1 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}  