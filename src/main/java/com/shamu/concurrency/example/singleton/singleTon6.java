package com.shamu.concurrency.example.singleton;

import com.shamu.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例的实例在类装载的时候创建
 * 缺点, 可能会造成资源的浪费和性能问题
 */
@ThreadSafe
public class singleTon6 {
    // 私有构造函数
    private singleTon6(){

    }

    //单例对象
    // 不同的静态代码块是按照代码顺序执行的
    private static singleTon6 instance;

    static {
        instance = new singleTon6();
    }

    //静态工厂方法
    public static singleTon6 getInstance(){
        return instance;
    }

    public static void main(String[] args){
        System.out.println(instance.hashCode());
        System.out.println(instance.hashCode());
    }
}
