package com.study.code.design_pattern.patterns.builder;

/**
 * @ClassName: HighHouse
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 15:33
 **/
public class HighHouse extends HouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println(" 高楼房子打地基100米 ... ");
    }

    @Override
    public void buildWalls() {
        System.out.println(" 高楼房子砌墙20cm ... ");
    }

    @Override
    public void roofed() {
        System.out.println(" 高楼房子封顶玻璃顶 ... ");
    }
}
