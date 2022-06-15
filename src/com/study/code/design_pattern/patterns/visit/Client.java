package com.study.code.design_pattern.patterns.visit;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/14 13:09
 **/
public class Client {
    public static void main(String[] args) {
        // 第一次测评
        ObjectStructure objectStructure = new ObjectStructure();
        // 添加两个人
        objectStructure.attach(new Woman());
        objectStructure.attach(new Man());

        // 给成功的评价
        objectStructure.display(new Success());

        // 给失败的评价
        objectStructure.display(new Fail());

        // 给待定的评价
        objectStructure.display(new Wait());
    }
}
