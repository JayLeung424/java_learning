package com.study.code.design_pattern.patterns.memento.theory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Caretaker
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/18 19:10
 **/
public class Caretaker {
    private Map<Integer, Memento> mementoMap = new HashMap<>();
    private int position = 1;

    public void add(Memento memento) {
        mementoMap.put(position, memento);
        position++;
    }

    /**
     * 获取到第index个Originator的备忘录对象 (即保存状态)
     *
     * @param index
     * @return
     */
    public Memento get(int index) {
        return mementoMap.get(index);
    }
}
