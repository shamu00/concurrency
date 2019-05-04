package com.shamu.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class synchronizedExp2 {

    //修饰一个类
    public static void test1(int j){
        synchronized (synchronizedExp2.class){

            for(int i = 0; i < 10; i++){
                log.info("test1 {} - {}", i, j);
            }
        }
    }

    //修饰一个静态方法
    public static synchronized void test2(int j){
        // 作用范围为syn括起来的部分, 作用对象为所有对象, 同一时间只有一个对象可以执行
        for(int i = 0; i < 10; i++){
            log.info("test1 {} - {}", i, j);
        }
    }

    public static void main(String[] args){
        synchronizedExp2 example1 = new synchronizedExp2();
        synchronizedExp2 example2 = new synchronizedExp2();
        //使用线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() ->{
            example1.test1(1);
        });
        executorService.execute(() ->{
            example2.test1(2);
        });
    }
}
