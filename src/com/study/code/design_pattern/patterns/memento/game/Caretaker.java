package com.study.code.design_pattern.patterns.memento.game;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: Caretaker
 * @Description: 守护者对象、 保存游戏角色状态
 * @Author: jiel
 * @Date: 2022/4/18 19:37
 **/
public class Caretaker {
    // 如果只保存一次状态
    private Memento memento;
    // 如果对GameRole 保存多个状态
    private List<Memento> mementoList;
    // 对多个游戏角色 保存多个状态
    private HashMap<String, List<Memento>> mementosMap;

    /**
     * 使用只保存一次状态的
     */
    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
