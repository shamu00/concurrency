package com.shamu.concurrency.example.singleton;

import com.shamu.concurrency.annotations.Recommend;
import com.shamu.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式: 最安全
 */
@ThreadSafe
@Recommend
public class singleTon7 {
    private singleTon7(){

    }

    public static singleTon7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private singleTon7 sin7;

        // JVM 保证这个方法只被调用一次
        Singleton(){
            sin7 = new singleTon7();
        }

        private singleTon7 getInstance(){
            return sin7;
        }
    }
}
