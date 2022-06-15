package com.study.code.design_pattern.patterns.builder;

/**
 * @ClassName: CommonHouse
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 15:32
 **/
// ConcreteBuilder 具体建造者
public class CommonHouse extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println(" 普通房子打地基5米 ... ");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 普通房子砌墙10cm ... ");
    }

    @Override
    public void roofed() {
        System.out.println(" 普通房子封顶茅草 ... ");
    }
}