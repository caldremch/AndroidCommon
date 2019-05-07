package com.caldremch.androidcommon;

import com.caldremch.common.base.BaseApp;

import leakcanary.LeakCanary;
import leakcanary.LeakSentry;

/**
 * @author Caldremch
 * @date 2019-04-25 17:14
 * @email caldremch@163.com
 * @describe
 **/
public class DemoApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakSentry.INSTANCE.getRefWatcher().watch(this);
    }
}
