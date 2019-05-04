package com.shamu.concurrency.example.singleton;


import com.shamu.concurrency.annotations.NotThreadSafe;
import com.shamu.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 -- 双重同步锁单例模式
 * 单例的实例在第一次启动的时候创建
 */
@ThreadSafe
public class singleTon5 {
    // 私有构造函数
    private singleTon5(){

    }

    // 1, memory = allocate() 分配对象的内存空间
    // 2, ctorInstance() 初始化对象
    // 3, instance = memory 设置instance指向刚分配的内存

    //单例对象
    //使用volatile + 双重检测机制禁止指令重排序
    private volatile static singleTon5 instance = null;

    //静态工厂方法
    public static singleTon5 getInstance(){
        if(instance == null){ // 双重检测机制
            synchronized (singleTon5.class){ // 锁
                if(instance == null){
                    instance = new singleTon5();
                }
            }
        }
        return instance;
    }
}
