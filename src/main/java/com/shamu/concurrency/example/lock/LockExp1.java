package com.shamu.concurrency.example.lock;

import com.shamu.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class LockExp1 {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute( () ->{
                try{
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    System.out.println("exception" +  e);
                }
                countDownLatch.countDown();
            });
        }
        //保证之前的所有进程执行完
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    //使用synchronized的修饰静态保证了线程安全性
    private synchronized static void add(){
        count++;
    }
}
