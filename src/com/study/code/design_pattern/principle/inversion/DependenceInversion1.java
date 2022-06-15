package com.study.code.design_pattern.principle.inversion;

/**
 * @ClassName: DependenceInversion1
 * @Description:
 * @Author: jiel
 * @Date: 2022/3/29 11:06
 **/

/**
 * 方式1 分析:
 * 1. 简单，容易想到
 * 2. 如果我们获取的对象是微信、短信等，那么我们要新增类，同时Person对象也要新增相应的方法
 * 3. 引入一个抽象的接口, 引入一个IReceiver，表示接收者， Person与IReceiver发生依赖
 * 因为Email、Wechat、短信等都数据IReceiver的接收返回，各自实现IReceiver的方法，就实现了依赖倒转原则了！
 */
public class DependenceInversion1 {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}

class Email {
    public String getInfo() {
        return "电子邮件信息";
    }
}

// 完成person 接受消息的功能
class Person {
    public void receive(Email email) {
        System.out.println(email.getInfo());
    }
}