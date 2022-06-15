package com.study.code.design_pattern.patterns.memento.game;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/18 19:43
 **/
public class Client {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        // 创建游戏角色
        GameRole gameRole = new GameRole();
        gameRole.setVit(100);
        gameRole.setDef(100);
        System.out.println(" 和Boss大战前的状态:");
        gameRole.display();

        // 保存当前状态
        caretaker.setMemento(gameRole.createMemento());

        System.out.println(" 和Boss大战");
        gameRole.setVit(30);
        gameRole.setDef(40);
        System.out.println(" 和Boss大战后的状态:");
        gameRole.display();

        // 回城恢复 初始状态
        System.out.println(" 回城恢复 恢复状态");
        gameRole.recoverGameRoleFromMemento(caretaker.getMemento());
        System.out.println(" 恢复后的状态:");
        gameRole.display();
    }
}
