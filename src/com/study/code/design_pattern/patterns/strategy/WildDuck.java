package com.study.code.design_pattern.patterns.strategy;

/**
 * @ClassName: WildDuck
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:17
 **/
public class WildDuck extends Duck {
    @Override
    public void dispaly() {
        System.out.println("这是一只野鸭子！");
    }
}
