package com.design.patterns.builder;

/**
 * @ClassName: HouseDirector
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 15:44
 **/
public class HouseDirector {

    HouseBuilder houseBuilder;

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public HouseBuilder getBuilder() {
        return houseBuilder;
    }

    public void setBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //如何处理建造房子的流程，交给指挥者
    public House constructHouse() {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}