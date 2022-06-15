package com.study.code.design_pattern.patterns.memento.game;

/**
 * @ClassName: Memento
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/18 19:36
 **/
public class Memento {
    // 攻击力
    int vit;
    // 防御力
    int def;

    public Memento(int vit, int def) {
        this.vit = vit;
        this.def = def;
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
