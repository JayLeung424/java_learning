package com.design.patterns.command;


public interface Command {
    // 开启
    public void execute();

    // 撤销
    public void undo();
}
