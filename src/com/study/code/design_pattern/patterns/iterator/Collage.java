package com.design.patterns.iterator;

import java.util.Iterator;

public interface Collage {
    /**
     * 返回名字
     *
     * @return
     */
    public String getName();

    /**
     * 新增专业
     *
     * @param name
     * @param desc
     */
    public void addDepartment(String name, String desc);

    /**
     * 创建Iterator
     *
     * @return
     */
    public Iterator createIterator();
}
