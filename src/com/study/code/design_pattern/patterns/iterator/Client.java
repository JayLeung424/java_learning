package com.design.patterns.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/15 10:54
 **/
public class Client {
    public static void main(String[] args) {
        List<Collage> collageList = new ArrayList<Collage>();
        collageList.add(new ComputerCollage());
        collageList.add(new InfoCollege());

        OutputImpl output = new OutputImpl(collageList);
        output.printCollege();
    }
}
