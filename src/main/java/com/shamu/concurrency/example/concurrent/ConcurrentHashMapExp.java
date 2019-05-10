package com.shamu.concurrency.example.concurrent;

import com.shamu.concurrency.annotations.NotThreadSafe;
import com.shamu.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentHashMapExp {
    public static int clientTotal = 5000;

    // 同时并发执行的线程数量
    public static int threadTotal = 200;

    //在栈封闭的地方可以使用, 确保线程安全
    public static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore  = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    update(count);
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
        log.info("size: {}", map.size());
    }
    private static void update(int i){
        map.put(i, i);
    }
}
