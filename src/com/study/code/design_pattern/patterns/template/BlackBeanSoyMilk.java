package com.design.patterns.template;

/**
 * @ClassName: BlackBeanSoyMilk
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 15:50
 **/
public class BlackBeanSoyMilk extends SoyMilk {
    @Override
    public void addCondiments() {
        System.out.println(" 加入黑豆   ...");
    }
}
