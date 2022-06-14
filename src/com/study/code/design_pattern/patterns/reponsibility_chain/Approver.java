package com.design.patterns.reponsibility_chain;

/**
 * @ClassName: Approver
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/20 14:55
 **/
public abstract class Approver {
    // 下一个处理者
    Approver approver;
    // 名字
    String name;

    public Approver(String name) {
        this.name = name;
    }

    /**
     * 设置下一个处理者
     *
     * @param approver
     */
    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    /**
     * 处理审批请求的方法 得到一个请求，处理是子类完成的 所以方法为抽象方法
     *
     * @param purchaseRequest
     */
    public abstract void processRequest(PurchaseRequest purchaseRequest);
}
