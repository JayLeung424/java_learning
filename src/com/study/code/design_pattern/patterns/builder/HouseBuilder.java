package com.study.code.design_pattern.patterns.builder;

/**
 * @ClassName: AbstractHouse
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 15:31
 **/
// 抽象建造者 - 流程
public abstract class HouseBuilder {
    protected House house = new House();

    // 打地基
    public abstract void buildBasic();

    // 砌墙
    public abstract void buildWalls();

    //封顶
    public abstract void roofed();

    // 建造房子
    public House buildHouse() {
        return this.house;
    }
}