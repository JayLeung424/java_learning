package com.design.patterns.visit;

/**
 * @ClassName: Fail
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/14 13:03
 **/
public class Fail implements Action {
    @Override
    public void getWomanResult(Woman woman) {
        System.out.println(" 女人给的评价是: 该歌手失败 !");
    }

    @Override
    public void getManResult(Man man) {
        System.out.println(" 男人给的评价是: 该歌手失败 !");
    }
}
