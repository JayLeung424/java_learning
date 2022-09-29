package com.study.code.juc.future.completable.mall;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: NetMall
 * @Description:
 * @Author: jiel
 * @Date: 2022/9/29 14:12
 **/

public class NetMall {
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    /**
     * 计算价格
     * TimeUnit.SECONDS.sleep(1); 睡眠1s 模拟查询页面
     * @param productName
     * @return
     */
    public BigDecimal calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0));
    }

    public String getNetMallName() {
        return netMallName;
    }
}
