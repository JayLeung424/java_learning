package com.study.code.design_pattern.patterns.memento.theory;

/**
 * @ClassName: Memento
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/18 19:06
 **/
public class Memento {
    private String state;

    // 构造器
    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
