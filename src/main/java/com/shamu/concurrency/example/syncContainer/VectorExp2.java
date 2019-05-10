package com.shamu.concurrency.example.syncContainer;

import com.shamu.concurrency.annotations.NotThreadSafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class VectorExp2 {
    // 报错java.util.ConcurrentModificationException
    // 在遍历的过程中, 如果进行remove操作, 会导致fastfail
    // 如果需要做删除操作, 可以先做标记, 标记完了之后统一进行删除
    // arrayList同样, 可见普通容器和同步容器都不支持并发操作
    private static void test1(List<Integer> list){  // for each
        for(Integer i: list){
            if(i.equals(3)){
                list.add(i);
            }
        }
    }

    //报错 java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1){  // iterator
        Iterator<Integer> iterator = v1.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    // 正常
    private static void test3(Vector<Integer> v1){
        for(int i = 0; i < v1.size(); i++){
            if(v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }
    // Vector, synchronizedList都会报错, 但是使用concurrent下面的CopyOnWriteArrayList不会报错
    private static List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args){
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        list.add(1);
        list.add(2);
        list.add(3);
        test1(list);
        //test2(vector);
        //test3(vector);
    }
}
