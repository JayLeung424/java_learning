package com.study.code.design_pattern.patterns.reponsibility_chain;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/20 15:03
 **/
public class Client {
    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 22100, 1);

        // 创建相关审批人
        DepartmentApprover departmentApprover = new DepartmentApprover("主任");
        CollegeApprover collegeApprover = new CollegeApprover("院长");
        ViceSchoolApprover viceSchoolApprover = new ViceSchoolApprover("副校长");
        SchoolApprover schoolApprover = new SchoolApprover("校长");

        // 设置上级
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolApprover);
        viceSchoolApprover.setApprover(schoolApprover);

        departmentApprover.processRequest(purchaseRequest);
    }
}
