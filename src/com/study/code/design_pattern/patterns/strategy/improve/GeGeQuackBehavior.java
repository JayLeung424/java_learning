package com.design.patterns.strategy.improve;

/**
 * @ClassName: GeGeQuackBehavior
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/21 19:44
 **/
public class GeGeQuackBehavior implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("鸭子咯咯叫～～～");
    }
}
