package com.study.code.design_pattern.patterns.command;

/**
 * @ClassName: LightOnCommand
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 21:17
 **/
public class LightOnCommand implements Command {
    // 聚合LightReceiver
    LightReceiver light;

    public LightOnCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // 认为execute表示打开就调用 on() 认为关闭就调用off()
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
