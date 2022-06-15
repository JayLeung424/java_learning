package com.study.code.design_pattern.patterns.command;


public interface Command {
    // 开启
    public void execute();

    // 撤销
    public void undo();
}
