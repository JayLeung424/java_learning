package com.design.patterns.reponsibility_chain;

/**
 * @ClassName: SchoolApprover
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/20 15:02
 **/
public class SchoolApprover extends Approver {

    public SchoolApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (30000f < request.getPrice()) {
            System.out.println(" 请求编号 id= " + request.getId() + "被" + this.name + "处理了");
        } else {
        }
    }
}

