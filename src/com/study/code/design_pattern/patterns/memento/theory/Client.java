package com.study.code.design_pattern.patterns.memento.theory;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/18 19:13
 **/
public class Client {
    public static void main(String[] args) {
        // 角色
        Originator originator = new Originator();
        // 备忘录
        Caretaker caretaker = new Caretaker();

        originator.setState(" 状态1 攻击力: 10");
        // 保存了当前状态
        caretaker.add(originator.saveStateMemento());
        originator.setState(" 状态2 攻击力: 50");
        caretaker.add(originator.saveStateMemento());
        originator.setState(" 状态3 攻击力: 100");
        caretaker.add(originator.saveStateMemento());
        System.out.println("当前状态是:" + originator.getState());

        // 希望得到状态1  将originator恢复到状态1
        // get(index) -> index是想要获取的第几次
        originator.getStateFromMemento(caretaker.get(2));
        System.out.println("恢复到状态1, 当前状态是:" + originator.getState());
    }
}
