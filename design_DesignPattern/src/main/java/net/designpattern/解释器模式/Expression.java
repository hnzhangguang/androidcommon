package net.designpattern.解释器模式;


/**
 * 解释器接口
 */
public interface Expression {
    public int interpret(Context context);
}  