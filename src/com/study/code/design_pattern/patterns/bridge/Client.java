package com.study.code.design_pattern.patterns.bridge;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/6 13:45
 **/
public class Client {
    public static void main(String[] args) {
        Phone phone = new FoldedPhone(new Vivo());
        phone.open();
        phone.call();
        phone.close();
    }
}
