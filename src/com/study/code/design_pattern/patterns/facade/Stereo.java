package com.study.code.design_pattern.patterns.facade;

/**
 * @ClassName: Stereo
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/10 17:03
 **/
public class Stereo {

    // 使用单例模式 - 恶汉式 肯定会用到
    private static Stereo instance = new Stereo();

    private Stereo() {
    }

    public static Stereo getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Stereo on ... ");
    }

    public void off() {
        System.out.println(" Stereo off ... ");
    }

    public void up() {
        System.out.println(" Stereo up ... ");
    }
}
