package com.caldremch.androidcommon;

import android.os.Handler;
import android.view.View;

import com.caldremch.common.base.BaseLoadingActivity;
import com.caldremch.common.widget.seekbar.SeekBarView;

public class MainActivity extends BaseLoadingActivity {

    private SeekBarView mSeekBar;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main_with_diy_view;
    }

    @Override
    public void initView() {
//        loadingTest();
        mSeekBar = findViewById(R.id.seekBar);

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
//        loadingTest();
        mSeekBar.stop();
    }

}
