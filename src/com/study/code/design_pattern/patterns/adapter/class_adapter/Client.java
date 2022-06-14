package com.design.patterns.adapter.class_adapter;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 17:31
 **/
public class Client {
    public static void main(String[] args) {
        new Phone().charging(new VoltageAdapter());
    }
}
