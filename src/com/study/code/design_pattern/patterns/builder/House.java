package com.design.patterns.builder;

/**
 * @ClassName: House
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 15:32
 **/
// Product 产品角色
public class House {
    private String basic;
    private String wall;
    private String roofed;
    // 生成getter setter

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getRoofed() {
        return roofed;
    }

    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }
}

