package com.study.code.design_pattern.patterns.state;

/**
 * 不能抽奖的状态
 */
public class NoRaffleState extends State {

    // 初始化时候传入活动引用, 扣除积分以后改变其状态
    RaffleActivity activity;

    public NoRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    /**
     * 当前状态可以扣积分 扣除以后 将状态设置为可以抽奖状态
     */
    @Override
    public void deductMoney() {
        System.out.println("扣除50积分成功, 你可以抽奖了");
        // 扣除积分成功 状态修改为可以抽奖
        activity.setState(activity.getCanRaffleState());
    }

    /**
     * 当前状态不可以抽奖
     *
     * @return
     */
    @Override
    public boolean raffle() {
        System.out.println("扣除了积分才可以抽奖哦");
        return false;
    }

    /**
     * 当前状态不能发奖品
     */
    @Override
    public void dispensePrize() {
        System.out.println("不能发奖品");
    }
}
