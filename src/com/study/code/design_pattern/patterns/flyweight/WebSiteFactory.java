package com.design.patterns.flyweight;

import java.util.HashMap;

/**
 * @ClassName: WebSiteFactory
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/11 11:25
 **/
public class WebSiteFactory {

    //集合， 充当池的作用
    private HashMap<String, WebSite> pool = new HashMap<>();

    //根据网站的类型， 返回一个网站, 如果没有就创建一个网站，并放入到池中,并返回
    public WebSite getWebSiteCategory(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebSite(type));
        }
        return (WebSite) pool.get(type);
    }

    public int getWebSiteSize() {
        return pool.size();
    }
}
