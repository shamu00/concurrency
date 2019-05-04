package com.shamu.concurrency.example.singleton;

import com.shamu.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例的实例在第一次启动的时候创建
 */
@NotThreadSafe
public class singleTon1 {
    // 私有构造函数
    private singleTon1(){

    }

    //单例对象
    private static singleTon1 instance = null;

    //静态工厂方法
    public static singleTon1 getInstance(){
        if(instance == null){
            instance = new singleTon1();
        }
        return instance;
    }
}
