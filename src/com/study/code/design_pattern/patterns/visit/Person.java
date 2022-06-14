package com.design.patterns.visit;

/**
 * @ClassName: Person
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/14 13:01
 **/
public abstract class Person {
    // 提供一个方法， 让访问者可以访问
    public abstract void accept(Action action);
}
