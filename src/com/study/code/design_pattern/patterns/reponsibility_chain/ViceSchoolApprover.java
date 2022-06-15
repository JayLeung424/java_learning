package com.study.code.design_pattern.patterns.reponsibility_chain;

/**
 * @ClassName: ViceSchoolApprover
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/20 15:02
 **/
public class ViceSchoolApprover extends Approver {

    public ViceSchoolApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (10000f < request.getPrice() && request.getPrice() <= 30000f) {
            System.out.println(" 请求编号 id= " + request.getId() + "被" + this.name + "处理了");
        } else {
            // 金额处理不了 让下一级处理
            approver.processRequest(request);
        }
    }
}

