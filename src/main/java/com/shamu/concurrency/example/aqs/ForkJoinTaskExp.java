package com.shamu.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
// ForkJoin 框架
// 设计思想, 先干完活的线程,  去帮助没有干完活的线程干活
public class ForkJoinTaskExp extends RecursiveTask<Integer> {
    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExp(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute(){
        int sum = 0;

        boolean canCompute = (end - start) <= threshold;
        if(canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }
        else{
            // 如果任务大于阈值, 分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTaskExp leftTask = new ForkJoinTaskExp(start, middle);
            ForkJoinTaskExp rightTask = new ForkJoinTaskExp(middle + 1, end);

            leftTask.fork();
            rightTask.fork();

            // 等待任务执行结束后合并结果
            int leftRes = leftTask.join();
            int rightRes = rightTask.join();

            // 合并子任务
            sum = leftRes + rightRes;
        }
        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 生成一个计算任务, 计算 1 + 2 + ... + 100
        ForkJoinTaskExp task = new ForkJoinTaskExp(1, 100);

        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);

        try{
            log.info("res: {}", result.get());
        }catch(Exception e){
            log.error("Exception", e);
        }
    }
}
