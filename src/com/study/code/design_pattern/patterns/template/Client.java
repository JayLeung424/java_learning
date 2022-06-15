package com.study.code.design_pattern.patterns.template;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 16:12
 **/
public class Client {
    public static void main(String[] args) {

        System.out.println("----制作黑豆豆浆----");
        SoyMilk blackBeanSoyMilk = new BlackBeanSoyMilk();
        blackBeanSoyMilk.make();

        System.out.println("----制作花生豆浆----");
        SoyMilk peanutSoyMilk = new PeanutSoyMilk();
        peanutSoyMilk.make();
    }
}
