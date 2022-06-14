package com.design.patterns.state;

import java.util.Random;

public class CanRaffleState extends State {

    RaffleActivity activity;

    public CanRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    /**
     * 已经扣除了积分 不能再扣了
     */
    @Override
    public void deductMoney() {
        System.out.println("已经扣去过积分了");
    }

    /**
     * 可以抽奖了 抽完奖 根据实际情况 设置新的状态
     * @return
     */
    @Override
    public boolean raffle() {
        System.out.println("正在抽奖，请稍等！");
        Random r = new Random();
        int num = r.nextInt(10);
        if(num == 0){
            // 设置活动状态为发放奖品 context
            activity.setState(activity.getDispenseState());
            return true;
        }else{
            System.out.println("很遗憾 没有抽中奖品!");
            // 改变状态为不能抽奖
            activity.setState(activity.getNoRaffleState());
            return false;
        }
    }

    @Override
    public void dispensePrize() {
        System.out.println("没中奖，不能发放奖品");
    }
}
