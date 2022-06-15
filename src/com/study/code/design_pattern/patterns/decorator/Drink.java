package com.study.code.design_pattern.patterns.decorator;

/**
 * @ClassName: Drink
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 15:37
 **/
public abstract class Drink {
    // 描述
    public String desc;
    // 价格
    public float price = 0f;

    // 计算价格 - 子类实现
    public abstract float cost();

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
