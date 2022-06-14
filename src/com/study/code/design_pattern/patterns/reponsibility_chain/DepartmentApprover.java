package com.design.patterns.reponsibility_chain;

/**
 * @ClassName: DepartmentApprover
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/20 14:58
 **/
public class DepartmentApprover extends Approver{

    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() <= 5000f){
            System.out.println(" 请求编号 id= " +request.getId() + "被"+this.name +"处理了");
        }else {
            // 金额处理不了 让下一级处理
           approver.processRequest(request);
        }
    }
}
