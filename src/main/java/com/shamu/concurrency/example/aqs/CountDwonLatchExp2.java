package com.shamu.concurrency.example.aqs;

import com.shamu.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@ThreadSafe
public class CountDwonLatchExp2 {

    private static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 0; i < threadCount; i++){
            final int threadNum = i;
            //sleep方法放在此处是无效的
            //Thread.sleep(1);
            exec.execute(() -> {
                try {
                    test(threadNum);
                }
                catch (Exception e){
                    log.error("exception", e);
                }
                finally {
                    // 线程执行完毕之后, 要调用countDown
                    countDownLatch.countDown();
                }
            });

        }
        // 输入参数等待时间, 实现有限等待
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        // 当前线程全部执行完毕之后, 才关闭线程
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info("{}", threadNum);

    }
}
