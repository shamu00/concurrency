package com.shamu.concurrency.example.immutable;

import com.shamu.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ThreadSafe
public class immutableExp2 {

    // final 修饰引用类型的时候, 只是引用的对象不能发生修改, 但是可以修改map的内容
    private static Map<Integer, Integer> map = new HashMap();

    static{
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        // 此方法绑定的map, 从此就不能再被修改了
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args){

        map.put(1, 3); // 会抛出异常
        log.info("{}", map.get(1));
    }


}
