package com.design.principle.inversion.improve;

/**
 * @ClassName: DependenceInversion2
 * @Description:
 * @Author: jiel
 * @Date: 2022/3/29 11:12
 **/

/**
 * 优化解析:
 * 引入一个抽象的接口, 引入一个IReceiver，表示接收者， Person与IReceiver发生依赖
 * 因为Email、Wechat、短信等都数据IReceiver的接收返回，各自实现IReceiver的方法，就实现了依赖倒转原则了！
 */
public class DependenceInversion2 {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
        person.receive(new Wechat());
    }
}

// 定义接口
interface IReceiver {
    public String getInfo();
}

class Email implements IReceiver {
    @Override
    public String getInfo() {
        return "电子邮件信息";
    }
}

class Wechat implements IReceiver {
    @Override
    public String getInfo() {
        return "微信信息";
    }
}

// 完成person 接受消息的功能
class Person {
    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}