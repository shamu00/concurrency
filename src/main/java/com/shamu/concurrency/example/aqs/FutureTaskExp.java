package com.shamu.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExp {

    public static void main(String[] args) throws Exception{
        //  定义一个任务
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>(){
            @Override
            public String call() throws Exception {
                log.info("do something in callable ");
                Thread.sleep(3000);
                return "Done";
            }
        });

        // 启动一个任务
        new Thread(futureTask).start();
        log.info("Do something in main");
        Thread.sleep(1000);
        String res = futureTask.get();
        log.info("{}", res);

    }
}
