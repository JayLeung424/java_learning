package com.study.code.design_pattern.patterns.singleton.enum_type;

/**
 * @ClassName: EnumSingleTest
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/2 13:23
 **/
public class EnumSingleTest {
    public static void main(String[] args) {
        Single instance1 = Single.INSTANCE;
        Single instance2 = Single.INSTANCE;
        System.out.println(instance1 == instance2);

    }
}

// 使用枚举 可以实现单例 推荐使用
enum Single {
    INSTANCE; // 属性

    public void sayOk() {
        System.out.println();
    }
}