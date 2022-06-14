package com.design.patterns.decorator.spice;

import com.design.patterns.decorator.Decorator;
import com.design.patterns.decorator.Drink;

/**
 * @ClassName: Chocolate
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:52
 **/
public class Chocolate extends Decorator {
    public Chocolate(Drink coffee) {
        super(coffee);
        setDesc(" 巧克力 ");
        setPrice(3f); // 巧克力的价格
    }
}
