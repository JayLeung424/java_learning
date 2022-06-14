package com.design.principle.singleresponsibility;

/**
 * @ClassName: SingleResponsibility3
 * @Description:
 * @Author: jiel
 * @Date: 2022/3/28 20:15
 **/
public class SingleResponsibility3 {
    /**
     * 方式3分析
     * 1. 修改的方式没有对原来的类做大的改变，只是增加方法
     * 2. 这里虽然没有在类上做到单一职责原则,但是在方法上做到了单一职责原则
     *
     * @param args
     */
    public static void main(String[] args) {
        NewVehicle newVehicle = new NewVehicle();
        newVehicle.airVehicleRun("飞机");
        newVehicle.roadVehicleRun("摩托车");
        newVehicle.waterVehicleRun("轮船");
    }
}

class NewVehicle {

    public void roadVehicleRun(String vehicle) {
        System.out.println("在陆地运行...");
    }

    public void airVehicleRun(String vehicle) {
        System.out.println("在天空运行...");
    }

    public void waterVehicleRun(String vehicle) {
        System.out.println("在海里运行...");
    }

}
