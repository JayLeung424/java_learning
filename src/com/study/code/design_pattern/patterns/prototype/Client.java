package com.design.patterns.prototype;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/4 19:15
 **/
public class Client {

    public static void main(String[] args) {
        Sheep friend = new Sheep("jack", 1, "白色");

        // 传统方法
        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.setFriend(friend);
        System.out.println(sheep);
        Sheep cloneSheep2 = (Sheep) sheep.clone();
        System.out.println(cloneSheep2);
        friend.setName("clone");
        sheep.setName("name-clone");
        System.out.println(sheep);
        System.out.println(cloneSheep2);


    }
}
