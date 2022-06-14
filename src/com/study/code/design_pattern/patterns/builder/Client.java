package com.design.patterns.builder;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 15:36
 **/
public class Client {
    public static void main(String[] args) {
        //盖普通房子
        CommonHouse commonHouse = new CommonHouse();
        //准备创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector(commonHouse);
        //完成盖房子，返回产品(普通房子)
        House house = houseDirector.constructHouse();

        HighHouse highHouse = new HighHouse();
        // 重置建造者
        houseDirector.setBuilder(highHouse);
        houseDirector.constructHouse();
    }
}
