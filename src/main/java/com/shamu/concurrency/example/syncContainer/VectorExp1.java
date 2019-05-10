package com.shamu.concurrency.example.syncContainer;

import com.shamu.concurrency.annotations.NotThreadSafe;

import java.util.Vector;

@NotThreadSafe
public class VectorExp1 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args){

        while(true) {
            for(int i = 0; i < 10; i++){
                vector.add(i);
            }
            Thread thread1 = new Thread() {
                public void run() {
                    // 此时, i读取的值是线程不安全的
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
