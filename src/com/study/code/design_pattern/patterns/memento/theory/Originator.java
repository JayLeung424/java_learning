package com.study.code.design_pattern.patterns.memento.theory;

/**
 * @ClassName: Originator
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/18 19:04
 **/
public class Originator {
    // 状态信息
    private String state;

    /**
     * 编写一个方法 返回一个状态对象Memento
     *
     * @return
     */
    public Memento saveStateMemento() {
        return new Memento(state);
    }

    /**
     * 通过备忘录对象 恢复状态
     *
     * @param memento
     */
    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
