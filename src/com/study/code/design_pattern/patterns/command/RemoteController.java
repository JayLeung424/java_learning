package com.study.code.design_pattern.patterns.command;

/**
 * @ClassName: RemoteController
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 21:24
 **/
public class RemoteController {

    // 开 按钮的命令数组
    Command[] onCommands;
    Command[] offCommands;

    // 执行撤销的命令
    Command undoCommand;


    // 构造器 完成对按钮的初始化

    public RemoteController() {
        this.onCommands = new Command[5];
        this.offCommands = new Command[5];
        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    // 给我们的按钮设置你需要的命令
    public void setCommand(int no, Command onCommand, Command offCommand) {
        this.onCommands[no] = onCommand;
        this.offCommands[no] = offCommand;
    }

    // 如果按下开的按钮
    public void onButtonWasPushed(int no) {
        // 找到你按下开的按钮 , 并调用相应的方法
        this.onCommands[no].execute();
        // 记录这次的操作 用于撤销
        this.undoCommand = this.onCommands[no];
    }

    // 如果按下关的按钮
    public void offButtonWasPushed(int no) {
        // 找到你按下开的按钮 , 并调用相应的方法
        this.offCommands[no].execute();
        // 记录这次的操作 用于撤销
        this.undoCommand = this.offCommands[no];
    }

    // 如果按下撤销的按钮
    public void undoButtonWasPushed(int no) {
        // 记录这次的操作 用于撤销
        this.undoCommand.undo();
    }
}
