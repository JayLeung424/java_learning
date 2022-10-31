package com.study.code.juc.cas;


import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: AutomicReferenceDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/31 16:24
 **/
public class AtomicReferenceDemo {
    public static void main(String[] args) {

        AtomicReference<User> atomicReference = new AtomicReference<>();

        User z3 = new User("张三",22);
        User l4 = new User("李四",28);

        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().toString());

    }
}
class User {
    String username;
    int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

