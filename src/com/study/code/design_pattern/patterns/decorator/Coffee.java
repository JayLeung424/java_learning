package com.design.patterns.decorator;

import com.design.patterns.decorator.Drink;

/**
 * @ClassName: Coffee
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:41
 **/
public class Coffee extends Drink {

    @Override
    public float cost() {
        return super.getPrice();
    }
}
