package com.design.patterns.command;

/**
 * @ClassName: LightOffCommand
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 21:20
 **/
public class LightOffCommand implements Command {
    // 聚合LightReceiver
    LightReceiver light;

    public LightOffCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
