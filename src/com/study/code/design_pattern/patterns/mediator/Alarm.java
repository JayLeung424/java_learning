package com.design.patterns.mediator;

public class Alarm extends Colleague {

    public Alarm(Mediator mediator, String name) {
        super(mediator, name);
        // 在创建Alarm同事对象时候, 将自己放到ConcreteMediator 对象集合中
        mediator.register(name, this);
    }

    public void sendAlarm(int stateChange) {
        sendMessage(stateChange);
    }

    @Override
    public void sendMessage(int stateChange) {
        // 调用的中介者对象的getMessage
        this.getMediator().getMessage(stateChange, this.name);
    }

}
