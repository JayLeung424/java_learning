package com.design.patterns.decorator.mycoffee;

import com.design.patterns.decorator.Coffee;

/**
 * @ClassName: LongBlackCoffee
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:44
 **/
public class LongBlackCoffee extends Coffee {
    public LongBlackCoffee() {
        setDesc("美式咖啡");
        setPrice(5f);
    }
}
