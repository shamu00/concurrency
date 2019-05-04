package com.shamu.concurrency.example.singleton;


import com.shamu.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式 -- 双重同步锁单例模式
 * 单例的实例在第一次启动的时候创建
 */
@NotThreadSafe
public class singleTon4 {
    // 私有构造函数
    private singleTon4(){

    }

    // 1, memory = allocate() 分配对象的内存空间
    // 2, ctorInstance() 初始化对象
    // 3, instance = memory 设置instance指向刚分配的内存

    //JVM 和 cpu优化, 发生了指令重排序
    // 1 -> 3 -> 2
    // 这个时候, 线程B 可能拿到了线程A创建的, 但是未进行初始化对象的实例

    //单例对象
    private static singleTon4 instance = null;

    //静态工厂方法
    public static singleTon4 getInstance(){
        if(instance == null){ // 双重检测机制
            synchronized (singleTon4.class){ // 锁
                if(instance == null){
                    instance = new singleTon4();
                }
            }
        }
        return instance;
    }
}
