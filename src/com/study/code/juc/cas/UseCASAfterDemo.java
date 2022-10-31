package com.study.code.juc.cas;

import com.study.code.design_pattern.patterns.interpreter.VarExpression;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: UseCaseAfterDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/31 14:40
 **/
public class UseCASAfterDemo {

    /**
     * 定义一个原子整型类
     */
    AtomicInteger atomicInteger = new AtomicInteger();

    public Integer getAtomicInteger() {
        return atomicInteger.get();
    }

    public void setAtomicInteger() {
        // 先get 再++
        this.atomicInteger.getAndIncrement();
    }
}
