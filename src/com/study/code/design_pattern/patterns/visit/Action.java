package com.study.code.design_pattern.patterns.visit;

public interface Action {
    // 得到女性的测评
    public void getWomanResult(Woman woman);

    // 得到男性的测评
    public void getManResult(Man man);
}
