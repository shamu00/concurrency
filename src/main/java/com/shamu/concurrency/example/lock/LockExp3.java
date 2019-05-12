package com.shamu.concurrency.example.lock;

import com.shamu.concurrency.annotations.ThreadSafe;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@ThreadSafe
public class LockExp3 {

    private final Map<String, Data> map = new TreeMap();

    // 有分别的读锁和写锁, 如果遇到读取情况比较多, 写入情况比较少的情况,
    // 可能会造成写饥饿
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value){
        // 悲观读写操作
        writeLock.lock();
        try{
            return map.put(key, value);
        }finally{
            writeLock.unlock();
        }
    }
    public static void main(String[] args) throws Exception {

    }

    class Data{

    }

}
