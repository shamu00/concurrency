package com.shamu.concurrency.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExp3 {
    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

//        executor.schedule(() -> {
//            log.warn("schedule run");
//        }, 3, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate( () -> {
            log.warn("Schedule run");
        }, 1, 3, TimeUnit.SECONDS);
//        executor.shutdown();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 1000);
    }
}
