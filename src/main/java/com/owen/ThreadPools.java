package com.owen;


import com.owen.model.MyTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by huang_b on 2018/3/28.
 * <p>
 * https://www.cnblogs.com/kuoAT/p/6714762.html
 */
public class ThreadPools {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }

        System.out.println("从例子可以看出，线程池会先创建corePoolSize个线程，还有任务进来就放到缓存队列中，还有任务进来就创建新的线程，直到达到 maximumPoolSize个线程,后续在来任务就抛出RejectedExecutionException,满了后的处理模式可以自定义 ");
        executor.shutdown();
    }
}
