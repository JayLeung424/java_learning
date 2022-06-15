package com.study.code.design_pattern.patterns.facade;

/**
 * @ClassName: TheaterLight
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/10 17:05
 **/
public class TheaterLight {

    // 使用单例模式 - 恶汉式 肯定会用到
    private static TheaterLight instance = new TheaterLight();

    private TheaterLight() {
    }

    public static TheaterLight getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" TheaterLight on ... ");
    }

    public void off() {
        System.out.println(" TheaterLight off ... ");
    }

    public void dim() {
        System.out.println(" TheaterLight dim ... ");
    }

    public void bright() {
        System.out.println(" TheaterLight bright ... ");
    }
}
