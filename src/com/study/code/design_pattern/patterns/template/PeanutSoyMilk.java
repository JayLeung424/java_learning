package com.design.patterns.template;

/**
 * @ClassName: PennutSoyMilk
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 15:53
 **/
public class PeanutSoyMilk extends SoyMilk {
    @Override
    public void addCondiments() {
        System.out.println("加入花生    ...");
    }
}
