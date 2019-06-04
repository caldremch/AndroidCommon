package com.caldremch.androidcommon.thread;

/**
 * @author Caldremch
 * @date 2019-06-04 17:54
 * @email caldremch@163.com
 * @describe
 **/
public interface ITimer {
    void start();
    void shutdown();
    void cancel();
}
