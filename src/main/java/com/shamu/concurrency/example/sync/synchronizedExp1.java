package com.shamu.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class synchronizedExp1 {
    public void test1(int j){

        //修饰一个代码块
        synchronized (this){
            //同步语句块, 作用范围为当前调用对象, 不同调用对象之间是互相不影响的
            for(int i = 0; i < 10; i++){
                log.info("test1 {} - {}", i, j);
            }
        }
    }

    //同步方法
    public synchronized void test2(int j){
    //作用范围为调用对象, 不同的调用对象之间互不影响
        for(int i = 0; i < 10; i++){
            log.info("test1 {} - {}", i, j);
        }
    }

    public static void main(String[] args){
        synchronizedExp1 example1 = new synchronizedExp1();
        synchronizedExp1 example2 = new synchronizedExp1();
        //使用线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() ->{
            example1.test2(1);
        });
        executorService.execute(() ->{
            example2.test2(2);
        });
    }
}
