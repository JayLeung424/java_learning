package com.study.code.design_pattern.patterns.template;

/**
 * @ClassName: SoyMilk
 * @Description:
 * @Author: jsu
 * @Date: 2022/4/12 15:45
 **/
public abstract class SoyMilk {

    // 模板方法, make , 模板方法可以做成 final , 不让子类去覆盖.
    public final void make() {
        select();
        if (customerWantCondiments()) {
            addCondiments();
        }
        soak();
        beat();
    }

    // 选材料
    public void select() {
        System.out.println("第一步: 选择好的新鲜黄豆 ");
    }

    // 添加不同的配料， 抽象方法, 子类具体实现
    public abstract void addCondiments();

    // 浸泡
    public void soak() {
        System.out.println("第三步: 黄豆和配料开始浸泡, 需要 3 小时");
    }

    // 打碎
    public void beat() {
        System.out.println("第四步: 黄豆和配料放到豆浆机去打碎");
    }

    // 钩子方法，决定是否需要添加配料
    // 在模板方法模式的父类中，我们可以定义一个方法，它默认不做任何事，子类可以视情况要不要覆盖它，该方法称为“钩子”
    boolean customerWantCondiments() {
        return true;
    }

}
