package com.study.code.design_pattern.patterns.reponsibility_chain;

/**
 * @ClassName: PurchaseRequest
 * @Description: 请求类
 * @Author: jiel
 * @Date: 2022/4/20 14:52
 **/
public class PurchaseRequest {

    // 请求类型
    private int type;
    // 请求金额
    private float price = 0.0f;

    private int id;

    public PurchaseRequest(int type, float price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
