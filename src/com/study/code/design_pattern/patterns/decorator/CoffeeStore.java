package com.design.patterns.decorator;

import com.design.patterns.decorator.mycoffee.LongBlackCoffee;
import com.design.patterns.decorator.spice.Chocolate;
import com.design.patterns.decorator.spice.Milk;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:55
 **/
public class CoffeeStore {
    public static void main(String[] args) {
        // 单点一份美式咖啡
        Drink drink = new LongBlackCoffee();
        System.out.println("价格: " + drink.cost());
        System.out.println("饮品信息: " + drink.getDesc());

        // 附加一份巧克力
        drink = new Chocolate(drink);
        System.out.println("价格: " + drink.cost());
        System.out.println("饮品信息: " + drink.getDesc());

        // 附加一份牛奶
        drink = new Milk(drink);
        System.out.println("价格: " + drink.cost());
        System.out.println("饮品信息: " + drink.getDesc());
    }
}
