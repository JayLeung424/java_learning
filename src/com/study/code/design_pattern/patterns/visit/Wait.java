package com.design.patterns.visit;

/**
 * @ClassName: Wait
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/14 13:23
 **/
public class Wait implements Action {
    @Override
    public void getWomanResult(Woman woman) {
        System.out.println(" 女人给的评价是: 该歌手待定 !");
    }

    @Override
    public void getManResult(Man man) {
        System.out.println(" 男人给的评价是: 该歌手待定 !");
    }
}