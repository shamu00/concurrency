package com.shamu.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExp2 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);
    public static void main(String[] args) throws Exception{

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() ->{
                try{
                    race(threadNum);
                }catch (Exception e){
                    log.error("exception", e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is read", threadNum + 1);
        try {
            // 如果想继续执行, 可以
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException | TimeoutException b){
            log.warn("BrokenBarrierException OR TimeOutException");
        }
        log.info("{} continue", threadNum + 1);
    }
}
