package com.study.code.design_pattern.patterns.facade;

/**
 * @ClassName: Popcorn
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/10 17:00
 **/
public class Popcorn {

    // 使用单例模式 - 恶汉式 肯定会用到
    private static Popcorn instance = new Popcorn();

    private Popcorn() {
    }

    public static Popcorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Popcorn on ... ");
    }

    public void off() {
        System.out.println(" Popcorn off ... ");
    }

    public void poping() {
        System.out.println(" Popcorn is poping ... ");
    }
}
