package com.study.code.design_pattern.patterns.flyweight;

/**
 * @ClassName: Client
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/11 11:27
 **/
public class Client {
    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        WebSite webSite = webSiteFactory.getWebSiteCategory("新闻");
        webSite.use(new User("阿三"));
        WebSite webSite1 = webSiteFactory.getWebSiteCategory("博客");
        webSite1.use(new User("Jayce"));
        WebSite webSite2 = webSiteFactory.getWebSiteCategory("博客");
        System.out.println("网站个数：" + webSiteFactory.getWebSiteSize());
    }
}
