package com.shamu.concurrency.example.atomic;

import com.shamu.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {
    //这个类用于更新这个类的某个实例的某个指定字段的值, 这个值要用 volatile 来修饰, 且非static
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if(updater.compareAndSet(example5, 100, 120)){
            log.info("update success {}", example5.getCount());
        }
        if(updater.compareAndSet(example5, 100, 120)){
            log.info("update success {}", example5.getCount());
        }
        else{
            log.info("update failed, {}", example5.getCount());
        }
    }

}