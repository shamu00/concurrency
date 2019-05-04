package com.shamu.concurrency.example.singleton;

import com.shamu.concurrency.annotations.NotRecommend;
import com.shamu.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 单例的实例在第一次启动的时候创建
 */
@ThreadSafe
@NotRecommend
public class singleTon3 {
    // 私有构造函数
    private singleTon3(){

    }

    //单例对象
    private static singleTon3 instance = null;

    //静态工厂方法
    //会带来性能的开销
    public static synchronized singleTon3 getInstance(){
        if(instance == null){
            instance = new singleTon3();
        }
        return instance;
    }
}
