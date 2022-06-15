package com.study.code.design_pattern.patterns.interpreter;

import java.util.HashMap;

/**
 * 抽象运算符号解释器 这里每个运算符号，都只和自己左右两个数字有关系
 * 但左右两个数字有可能也是一个解析的结果， 无论那种类型，都是Expression的子类
 */
public class SymbolExpression extends Expression {

    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * 因为 SymbolExpression 是让其子类来实现， 所以interpreter是一个默认实现
     *
     * @param var
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}
