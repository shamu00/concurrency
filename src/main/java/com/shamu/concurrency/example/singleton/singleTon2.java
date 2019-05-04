package com.shamu.concurrency.example.singleton;

import com.shamu.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例的实例在类装载的时候创建
 * 缺点, 可能会造成资源的浪费和性能问题
 */
@ThreadSafe
public class singleTon2 {
    // 私有构造函数
    private singleTon2(){

    }

    //单例对象
    private static singleTon2 instance = new singleTon2();

    //静态工厂方法
    public static singleTon2 getInstance(){

        return instance;
    }
}
