package net.designpattern.解释器模式;


/**
 * 减法解释器
 */
public class Minus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1() - context.getNum2();
    }
}  