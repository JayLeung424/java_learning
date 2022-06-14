package com.design.patterns.flyweight;

/**
 * @ClassName: User
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/11 11:29
 **/
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
