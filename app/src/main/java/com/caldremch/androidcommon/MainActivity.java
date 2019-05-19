package com.caldremch.androidcommon;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.caldremch.common.base.BaseLoadingActivity;

public class MainActivity extends BaseLoadingActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main_with_diy_view;
    }

    @Override
    public void initView() {
        loadingTest();

    }

    private void loadingTest() {
        onLoadingStatus();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onContentStatus();
            }
        }, 2000);
    }

    public void open(View view) {
        loadingTest();
    }

}
