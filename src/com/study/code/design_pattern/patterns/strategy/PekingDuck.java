package com.design.patterns.strategy;


/**
 * @ClassName: PekingDuck
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:18
 **/
public class PekingDuck extends Duck {
    @Override
    public void dispaly() {
        System.out.println("这是一只北京鸭子");
    }

    // 因为北京鸭子 不可以飞翔 所以需要重新写fly()
    @Override
    public void fly(){
        System.out.println("北京鸭子不能飞翔");
    }
}
