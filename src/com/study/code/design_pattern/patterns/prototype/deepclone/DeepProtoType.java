package com.study.code.design_pattern.patterns.prototype.deepclone;

import java.io.*;

/**
 * @ClassName: DeepProtoType
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/5 14:38
 **/
public class DeepProtoType implements Serializable, Cloneable {
    // String 属性
    public String name;
    // 引用类型
    public DeepCloneableTarget deepCloneableTarget;

    public DeepProtoType() {
        super();
    }

    // 深拷贝方式1 - 使用clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        // 完成对基本数据类型(属性) 和 String 的克隆
        deep = super.clone();
        // 对引用类型的属性 进行单独的处理
        DeepProtoType deepProtoType = (DeepProtoType) deep;

        // 调用引用类型的clone()方法
        deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();
        return deep;
    }

    // 深拷贝方式2 - 通过对象的序列化实现
    public Object deepClone() {
        // 创建流对象
        // 输出流
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        // 输入流
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            // 序列化操作
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); // 当前这个对象以流的方式输出

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepProtoType copyObj = (DeepProtoType) ois.readObject();
            return copyObj;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            // 关闭流
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
