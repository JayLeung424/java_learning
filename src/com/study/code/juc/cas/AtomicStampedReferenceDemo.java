package com.study.code.juc.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName: AtomicSteampReferenceDemo
 * @Description:
 * @Author: jiel
 * @Date: 2022/10/31 18:48
 **/
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {

        Book javaBook = new Book(1, "java");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<Book>(javaBook, 1);

        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());

        Book sql = new Book(2, "sql");
        boolean b;
        // 希望原来的书 java 现在将sql放进去  版本号希望是1 换成2
        b = stampedReference.compareAndSet(javaBook, sql, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        // 希望换回来  将sql 换为sql
        b = stampedReference.compareAndSet(sql, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
    }
}

class Book {
    private int id;
    String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
