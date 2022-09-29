package com.study.code.juc.future.completable.mall;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @ClassName: CompletableFutureMallDemo
 * @Description:
 * 案例说明 ：电商比价需求， 模拟如下情况
 * 1、需求：
 * 1.1 同一款产品, 同时搜索出同款产品在各大电商平台的售价
 * 1.2 同一款产品, 同时搜索出本产品在同一个电商平台下，各个入住卖家售价是多少
 *
 * 2、输出：出来的结果希望是同款产品的在不同地方的价格列表  -> 返回一个List<String>
 *    《MySQL》 in jd  price is 88.05
 *    《MySQL》 in dangdang  price is 86.11
 *    《MySQL》 in tb  price is 90.55
 *
 * 3、技术要求
 * 3.1 函数式编程
 * 3.2 链式编程
 * 3.3 Stream流式计算
 *
 * 切记，功能→性能
 *
 * 经常出现在等待某条 SQL 执行完成后，再继续执行下一条 SQL ，
 * 而这两条 SQL 本身是并无关系的，可以同时进行执行的。我们希望能够两条 SQL 同时进行处理，而不是等待其中的某一条 SQL 完成后，再继续下一条。同理， 对于分布式微服务的调用，按照实际业务，如果是无关联step by step的业务，可以尝试是否可以多箭齐发，同时调用。我们去比同一个商品在各个平台上的价格，要求获得一个清单列表， 1 step by step，查完京东查淘宝，查完淘宝查天猫......
 *
 *
 * @Author: jiel
 * @Date: 2022/9/29 11:35
 **/
public class CompletableFutureMallDemo {

    static List<NetMall> netMalls = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("tb"));

    /**
     * step by step 一家家搜查
     * @param netMalls
     * @param productName
     * @return
     */
    public static List<String> findPriceSync(List<NetMall> netMalls,String productName){
        return netMalls
                .stream()
                .map(mall -> String.format(productName+" %s price is %s",mall.getNetMallName(),mall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    /**
     * 异步查询 万箭齐发
     * @param netMalls
     * @param productName
     * @return
     */
    public static List<String> findPriceASync(List<NetMall> netMalls,String productName){
        return netMalls
                .stream()
                .map(mall -> CompletableFuture.supplyAsync(() -> String.format(productName + " %s price is %s", mall.getNetMallName(), mall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // List<String> products = findPriceSync(netMalls, "mysql");  // 3120毫秒
        List<String> products = findPriceASync(netMalls, "mysql"); // 1196毫秒
        for (String product : products) {
            System.out.println(product);
        }
        long endTime = System.currentTimeMillis();

        System.out.println(" --- costTime: "+(endTime - startTime) + "毫秒");
    }
}
