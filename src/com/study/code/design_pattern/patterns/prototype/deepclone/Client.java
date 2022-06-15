package com.study.code.design_pattern.patterns.prototype.deepclone;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 14:47
 **/
public class Client {
    public static void main(String[] args) throws Exception {
// TODO Auto-generated method stub
        DeepProtoType p = new DeepProtoType();
        p.name = "宋江";
        p.deepCloneableTarget = new DeepCloneableTarget("大牛", "小牛");
        //方式 1  完成深拷贝
        DeepProtoType p2 = (DeepProtoType) p.clone();
        //
        System.out.println("p1.name=" + p.name + "\tp1.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
        System.out.println("p2.name=" + p.name + "\tp2.deepCloneableTarget=" + p2.deepCloneableTarget.hashCode());

        //方式 2  完成深拷贝
//        DeepProtoType p2 = (DeepProtoType) p.deepClone();
//        System.out.println("p.name=" + p.name + "p.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
//        System.out.println("p2.name=" + p.name + "p2.deepCloneableTarget=" + p2.deepCloneableTarget.hashCode());
    }
}

