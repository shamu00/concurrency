package com.shamu.concurrency.example.immutable;

import com.shamu.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@NotThreadSafe
public class immutableExp1 {

    private final static Integer a = 1;
    private final static String b = "2";
    // final 修饰引用类型的时候, 只是引用的对象不能发生修改, 但是可以修改map的内容
    private final static Map<Integer, Integer> map = new HashMap();

    static{
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args){
        //
        //map = new HashMap<>();

        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    private void test(final int a){
        // final 的其他写法
    }
}
