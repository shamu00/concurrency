package com.shamu.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExp3 {

    private static int threadCount = 20;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(2);

        for(int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // 如果没有获得许可, 有限等待, 超时放弃
                    if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) { // 尝试获得许可
                        test(threadNum);
                        semaphore.release(); // 释放许可
                    }
                }
                catch (Exception e){
                    log.error("exception", e);
                }

            });

        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
