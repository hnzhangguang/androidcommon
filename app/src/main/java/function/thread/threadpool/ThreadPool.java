package function.thread.threadpool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPool extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * 缓存线程池
     * 创建一个可缓存的无界线程池，该方法无参数。
     * 当线程池中的线程空闲时间超过60s则会自动回收 该线程，当任务超过线程池的线程数则创建新线程。
     * 线程池的大小上限为Integer.MAX_VALUE，可 看做是无限大
     */
    public void cachedThreadPoolDemo() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", index=" + index);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * newFixedThreadPool
     * 创建一个固定大小的线程池，该方法可指定线程池的固定大小，对于超出的线程会在 LinkedBlockingQueue 队列中等待。
     */
    public void fixedThreadPoolDemo() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 6; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", index=" + index);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * newSingleThreadExecutor
     * 创建一个只有线程的线程池，该方法无参数，所有任务都保存队列LinkedBlockingQueue中，
     * 等待 唯一的单线程来执行任务，并保证所有任务按照指定顺序(FIFO或优先级)执行
     */
    public void singleThreadExecutorDemo() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ", index=" + index);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 创建一个可定时执行或周期执行任务的线程池，该方法可指定线程池的核心线程个数。
     */
    public void scheduledThreadPoolDemo() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //定时执行一次的任务，延迟1s后执行
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", delay 1s");
            }
        }, 1, TimeUnit.SECONDS);
        //周期性地执行任务，延迟2s后，每3s一次地周期性执行任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", every 3s");
            }
        }, 2, 3, TimeUnit.SECONDS);
    }


}
