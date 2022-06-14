package com.design.patterns.visit;

/**
 * @ClassName: Man
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/14 13:03
 **/
public class Man extends Person {
    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
