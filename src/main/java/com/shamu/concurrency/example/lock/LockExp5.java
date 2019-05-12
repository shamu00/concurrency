package com.shamu.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExp5 {
    public static void main(String[] args){
        ReentrantLock lock = new ReentrantLock();

        // 维护了一个等待信号的类, 实际使用情况比较少
        Condition condtion = lock.newCondition();

        // Thread1
        new Thread(() -> {
            try{
                lock.lock();
                log.info("wait singal"); // 1
                condtion.await(); // unlock
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            lock.unlock();
        }).start();

        // Thread2
        new Thread( () -> {
            lock.lock();
            log.info("get lock"); // 2
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            // send signal
            condtion.signalAll();
            log.info("send singnal ~"); // 3
            lock.unlock();
        }).start();
    }
}
