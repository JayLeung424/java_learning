package com.design.patterns.decorator.mycoffee;

import com.design.patterns.decorator.Coffee;

/**
 * @ClassName: EspressoCoffee
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:43
 **/
public class EspressoCoffee extends Coffee {
    public EspressoCoffee() {
        setDesc("意大利咖啡");
        setPrice(6f);
    }
}
