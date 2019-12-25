package net.designpattern.桥梁模式;


/**
 * Oracle 连接数据库
 */
public class SourceSub2 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the second sub!");
    }
}  