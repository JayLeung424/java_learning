package com.study.code.design_pattern.patterns.command;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/12 21:32
 **/
public class Client {
    public static void main(String[] args) {
//        Command command = new Command();
        // 使用命令设计模式 完成通过遥控器 完成对电灯的操作

        // 创建电灯的对象(接受者)
        LightReceiver lightReceiver = new LightReceiver();

        // 创建电灯打开&关闭的命令
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);

        // 遥控器
        RemoteController remoteController = new RemoteController();

        // 给我们的遥控器设置命令， 比如no = 0 是电灯开和关的操作
        remoteController.setCommand(0, lightOnCommand, lightOffCommand);

        System.out.println(" ------ 按下灯开的按钮 ------");
        remoteController.onButtonWasPushed(0);
        System.out.println(" ------ 按下灯关的按钮 ------");
        remoteController.offButtonWasPushed(0);
        // 撤销
        System.out.println(" ------ 按下撤销的按钮 ------");
        remoteController.undoButtonWasPushed(0);

    }
}
