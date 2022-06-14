package com.design.patterns.facade;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/10 17:07
 **/
public class Client {
    public static void main(String[] args) {
        // 如果直接调用各个设备的方法 会很麻烦!
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();
        homeTheaterFacade.pause();
        homeTheaterFacade.end();
    }
}
