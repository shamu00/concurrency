package com.shamu.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExp1 {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i++){
            final int index = i;
            executor.execute(() -> {
                log.info("task {}", index);
            });
        }
        executor.shutdown();
    }
}
