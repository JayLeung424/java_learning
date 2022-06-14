package com.design.patterns.mediator;

public class ClientTest {

    public static void main(String[] args) {
        // 创建一个中介者对象
        Mediator mediator = new ConcreteMediator();

        // 创建Alarm 并加入到 ConcreteMediator 对象的hashMap中

        // 创建 tv、alarm、创建curtains、coffeeMachine 并加入到 ConcreteMediator 对象的hashMap中
        TV tV = new TV(mediator, "TV");
        Alarm alarm = new Alarm(mediator, "alarm");
        Curtains curtains = new Curtains(mediator, "curtains");
        CoffeeMachine coffeeMachine = new CoffeeMachine(mediator, "coffeeMachine");

        // 闹钟发出消息
        alarm.sendAlarm(0);
        coffeeMachine.finishCoffee();
        alarm.sendAlarm(1);
    }

}
