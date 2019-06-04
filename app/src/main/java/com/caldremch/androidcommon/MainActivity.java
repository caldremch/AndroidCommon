package com.caldremch.androidcommon;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.caldremch.androidcommon.thread.ITimer;
import com.caldremch.androidcommon.thread.ITimerCallback;
import com.caldremch.androidcommon.thread.ThreadPoolTimer;
import com.caldremch.common.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            button.setText(String.valueOf(i));
        }
    };


    Button button;
    private ITimer timer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_with_diy_view;

    }

    @Override
    public void initView() {
        button = findViewById(R.id.button);
    }

    int i = 0;

    @Override
    public void initData() {
        timer = new ThreadPoolTimer(handler, button);
        timer.start();
    }

    public void startBtn(View view) {
        timer.start();
    }

    public void stopBtn(View view) {
        timer.cancel();
    }

    public void stutDownBtn(View view) {
        timer.shutdown();
    }
}
