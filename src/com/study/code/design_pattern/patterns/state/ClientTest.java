package com.study.code.design_pattern.patterns.state;


public class ClientTest {

    public static void main(String[] args) {
        // 创建活动对象 奖品有1个奖品
        RaffleActivity activity = new RaffleActivity(1);

        // 连续抽奖300次
        for (int i = 0; i < 300; i++) {
            System.out.println("--------第" + (i + 1) + "次抽奖----------");
            // 参加抽奖 第一步点击扣去积分
            activity.deductMoney();

            // 第二步抽奖
            activity.raffle();
        }
    }

}
