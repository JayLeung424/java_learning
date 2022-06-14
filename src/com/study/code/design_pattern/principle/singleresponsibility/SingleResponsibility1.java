package com.design.principle.singleresponsibility;

/**
 * @ClassName: SingleResponsibility1
 * @Description:
 * @Author: jiel
 * @Date: 2022/3/28 20:04
 **/
public class SingleResponsibility1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        // 三个交通工具
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");
    }
}


/**
 * Vehicle 交通工具类
 * 方式 1
 * 1. 再方式一的run方法中，违反了单一职责原则
 * 2. 解决方法很简单，根据交通工具运行方式的不同，分解成不同类即可
 */
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上运行...");
    }
}