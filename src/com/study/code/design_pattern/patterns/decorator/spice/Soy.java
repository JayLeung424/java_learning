package com.design.patterns.decorator.spice;

import com.design.patterns.decorator.Decorator;
import com.design.patterns.decorator.Drink;

/**
 * @ClassName: Soy
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:54
 **/
public class Soy extends Decorator {
    public Soy(Drink coffee) {
        super(coffee);
        setDesc(" 豆浆 ");
        setPrice(1f);
    }
}
