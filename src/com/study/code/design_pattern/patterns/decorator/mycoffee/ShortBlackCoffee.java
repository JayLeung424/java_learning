package com.design.patterns.decorator.mycoffee;

import com.design.patterns.decorator.Coffee;

/**
 * @ClassName: ShortBlackCoffee
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:45
 **/
public class ShortBlackCoffee extends Coffee {
    public ShortBlackCoffee() {
        setDesc("short black coffee");
        setPrice(7.f);
    }
}
