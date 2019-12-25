package net.designpattern.解释器模式;


/**
 * 对一个对象,不同的解释器,解释的结果不同
 */
public class Test {

    public static void main(String[] args) {

        // 计算9+2-8的值  
        int result = new Minus().interpret((new Context(new Plus()
                .interpret(new Context(9, 2)), 8)));
        System.out.println(result);

        //最后输出正确的结果：3。
    }
}  