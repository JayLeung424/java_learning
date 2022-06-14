package com.design.patterns.decorator.spice;

import com.design.patterns.decorator.Decorator;
import com.design.patterns.decorator.Drink;

/**
 * @ClassName: Milk
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:54
 **/
public class Milk extends Decorator {
    public Milk(Drink coffee) {
        super(coffee);
        setDesc(" 牛奶 ");
        setPrice(2f);
    }
}
