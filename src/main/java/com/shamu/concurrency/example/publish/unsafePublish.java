package com.shamu.concurrency.example.publish;

import com.shamu.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class unsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args){
        unsafePublish unsafe = new unsafePublish();
        log.info("{}", Arrays.toString(unsafe.getStates()));

        // 可以修改数组的值, 导致线程不安全, 因为无法确定是否被修改
        unsafe.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafe.getStates()));
    }
}
