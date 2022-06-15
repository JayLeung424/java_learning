package com.study.code.design_pattern.patterns.command;

/**
 * @ClassName: NoCommand
 * @Description: 没有任何命令 即空执行:  用于初始化按钮, 当调用空命令的时候 对象什么都不做
 * 其实 这样也是一种设计模式， 可以省掉对空的判断
 * @Author: jiel
 * @Date: 2022/4/12 21:21
 **/
public class NoCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
