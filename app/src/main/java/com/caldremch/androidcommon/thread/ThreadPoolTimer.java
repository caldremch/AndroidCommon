package com.caldremch.androidcommon.thread;

import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Caldremch
 * @date 2019-06-04 17:53
 * @email caldremch@163.com
 * @describe
 **/
public class ThreadPoolTimer implements ITimer {

    private static final String TAG = "ThreadPoolTimer";

    private ITimerCallback callback;

    private Handler handler;

    private Button button;

    public ThreadPoolTimer(Handler handler, Button button) {
        this.handler = handler;
        this.button = button;
    }

    public ThreadPoolTimer(ITimerCallback callback) {
        this.callback = callback;
    }

    public class BasicThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("定时器-ScheduledExecutorService-Thread");
            return thread;
        }
    }

    private ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1
            , new BasicThreadFactory()
    );

    private Future future;

    int i = 0;

    @Override
    public void start() {

        //线程池关闭
        if (service.isShutdown()) {
            System.out.println("已经关闭");
            return;
        }

        //任务正在执行
        if (future != null && !future.isDone()) {
            System.out.println("任务正在执行");
            return;
        }

        System.out.println("开始任务");
        //开始任务
        future = service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                try {
                    i++;
                    System.out.println(i);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            button.setText(String.valueOf(i));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

    }

    @Override
    public void shutdown() {
        if (service != null) {
            service.shutdownNow();
        }
    }

    @Override
    public void cancel() {
        if (future != null) {
            future.cancel(true);
        }
    }

}
