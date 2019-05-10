package com.shamu.concurrency.example.threadLocal;

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        //请求进入后端服务器, 但是还没有进行实际处理的时候
        requestHolder.set(id);
    }

    public static Long getId(){
        // 获取数据
        return requestHolder.get();
    }

    public static void remove(){
        // 在这个接口被处理完之后, 释放内存
        requestHolder.remove();
    }
}
