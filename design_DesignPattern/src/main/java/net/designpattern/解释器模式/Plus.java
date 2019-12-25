package net.designpattern.解释器模式;


/**
 * 加法解释器
 */
public class Plus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1() + context.getNum2();
    }
}  