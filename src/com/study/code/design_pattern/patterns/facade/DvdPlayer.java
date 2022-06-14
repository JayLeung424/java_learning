package com.design.patterns.facade;

/**
 * @ClassName: DVDPlayer
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/10 16:57
 **/
public class DvdPlayer {

    // 使用单例模式 - 恶汉式 肯定会用到
    private static DvdPlayer instance = new DvdPlayer();

    private DvdPlayer() {
    }

    public static DvdPlayer getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" DVD on ... ");
    }

    public void off() {
        System.out.println(" DVD off ... ");
    }

    public void play() {
        System.out.println(" DVD is play ... ");
    }

    public void pause() {
        System.out.println(" DVD pause ... ");
    }
}
