package com.study.code.design_pattern.principle.singleresponsibility;

/**
 * @ClassName: SingleResponsibility2
 * @Description:
 * @Author: jiel
 * @Date: 2022/3/28 20:15
 **/
public class SingleResponsibility2 {
    /**
     * 方法2分析:
     * 1. 有遵守单一职责原则
     * 2. 改动过大，将类分解，同时修改客户端
     * 3. 直接修改Vehicle类，改动代码少!
     **/
    public static void main(String[] args) {
        new RoadVehicle().run("摩托车");
        new AirVehicle().run("飞机");
        new WaterVehicle().run("摩托车");
    }
}

class RoadVehicle {
    public void run(String vehicle) {
        System.out.println("在陆地运行...");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println("在天空运行...");
    }
}

class WaterVehicle {
    public void run(String vehicle) {
        System.out.println("在海里运行...");
    }
}