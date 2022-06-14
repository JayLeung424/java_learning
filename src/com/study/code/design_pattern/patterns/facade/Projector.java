package com.design.patterns.facade;

/**
 * @ClassName: Projector
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/10 17:02
 **/
public class Projector {

    // 使用单例模式 - 恶汉式 肯定会用到
    private static Projector instance = new Projector();

    private Projector() {
    }

    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Projector on ... ");
    }

    public void off() {
        System.out.println(" Projector off ... ");
    }

    public void focus() {
        System.out.println(" Projector is focus ... ");
    }

}
