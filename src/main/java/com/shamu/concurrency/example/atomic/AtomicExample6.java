package com.shamu.concurrency.example.atomic;

import com.shamu.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
@Slf4j
@ThreadSafe
public class AtomicExample6 {

    private static AtomicBoolean isHappeded = new AtomicBoolean(false);

    public static int clientTotal = 50;

    public static int threadTotal = 20;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() ->{
                try{
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }
                catch(Exception e){
                    log.error("Exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened:{}", isHappeded.get());
    }
    private static void test(){
        if(isHappeded.compareAndSet(false, true)){
            log.info("execute");
        }

    }
}