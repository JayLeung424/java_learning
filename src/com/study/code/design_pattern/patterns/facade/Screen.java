package com.study.code.design_pattern.patterns.facade;

/**
 * @ClassName: Screen
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/10 17:02
 **/
public class Screen {

    // 使用单例模式 - 恶汉式 肯定会用到
    private static Screen instance = new Screen();

    private Screen() {
    }

    public static Screen getInstance() {
        return instance;
    }

    public void up() {
        System.out.println(" Screen up ... ");
    }

    public void down() {
        System.out.println(" Screen down ... ");
    }
}
