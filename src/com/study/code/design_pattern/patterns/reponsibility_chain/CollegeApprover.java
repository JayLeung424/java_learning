package com.study.code.design_pattern.patterns.reponsibility_chain;

/**
 * @ClassName: CollegeApprover
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/20 15:01
 **/
public class CollegeApprover extends Approver {

    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (5000f < request.getPrice() && request.getPrice() <= 10000f) {
            System.out.println(" 请求编号 id= " + request.getId() + "被" + this.name + "处理了");
        } else {
            // 金额处理不了 让下一级处理
            approver.processRequest(request);
        }
    }
}

