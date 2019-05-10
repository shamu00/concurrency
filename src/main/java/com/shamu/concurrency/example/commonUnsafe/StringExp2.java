package com.shamu.concurrency.example.commonUnsafe;

import com.shamu.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class StringExp2 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数量
    public static int threadTotal = 200;

    public static StringBuffer sb = new StringBuffer();

    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore  = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    update();
                    semaphore.release();
                }
                catch(Exception e){
                    log.info("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", sb.length());
    }
    private static void update(){
        sb.append('1');
    }
}
