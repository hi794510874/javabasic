package com.owen;

import com.owen.model.MyCallable;
import com.owen.model.MyRunnable;
import com.owen.model.MyThread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/*
* 各种线程   线程池
*
* http://www.importnew.com/21136.html
* */
public class ThreadVarious {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /*创建线程  方法1  直接继承 Thread类 重写 run方法  没有返回值  没有参数*/
        MyThread myThread = new MyThread();
        myThread.setParam("带参数处理");
        myThread.start();//标示 已准备就绪 等待被调用

        /*创建线程 方法2  实现myrunnable接口 然后把对象传到 thread里面  没有返回值  没有参数*/
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.setParam("带参数");
        Thread th = new Thread(myRunnable);
        th.start();

        HasResultThread();
    }

    /*
    *有返回结果的 线程执行
    * */
    private static void HasResultThread() throws ExecutionException, InterruptedException {

        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }

}
