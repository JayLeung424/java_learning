package com.study.code.design_pattern.principle.liskov;

/**
 * @ClassName: Liskvo
 * @Description:
 * @Author: jiel
 * @Date: 2022/3/29 14:49
 **/
public class Liskov {
}

class A {
    // 返回两个数的差
    public int func1(int a, int b) {
        return a - b;
    }
}

// 新增一个功能 完成两个数的相加
class B extends A {
    // 重写了A类的方法
    @Override
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return a + b;
    }
}