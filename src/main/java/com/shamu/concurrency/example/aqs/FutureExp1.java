package com.shamu.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExp1 {

    static class MyCallable  implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "DONE";
        }
    }

    public static void main(String[] args) throws Exception{

        ExecutorService executor = Executors.newCachedThreadPool();
        // 能创建一个线程帮我们做一些事情, 结果后面才需要的
        Future<String> future = executor.submit(new MyCallable());
        log.info("Do something in main");
        Thread.sleep(1000);
        String res = future.get();
        log.info("{}", res);
        executor.shutdown();
    }
}
