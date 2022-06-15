package com.study.code.design_pattern.patterns.memento.game;

/**
 * @ClassName: GameRole
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/18 19:40
 **/
public class GameRole {
    // 攻击力
    int vit;
    // 防御力
    int def;

    // 创建memento, 即根据当前状态得到memento
    public Memento createMemento() {
        return new Memento(vit, def);
    }

    // 从备忘录对象恢复GameRole状态
    public void recoverGameRoleFromMemento(Memento memento) {
        this.vit = memento.getVit();
        this.def = memento.getDef();
    }

    // 显示当前游戏的角色
    public void display() {
        System.out.println("游戏角色当前的攻击力 : " + this.vit + "\t 防御力: " + this.def);
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
