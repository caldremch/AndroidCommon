package com.caldremch.common.widget.seekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.caldremch.common.R;

import java.util.Locale;

/**
 * @author Caldremch
 * @date 2019-06-01 11:34
 * @email caldremch@163.com
 * @describe
 **/
public class SeekBarView extends FrameLayout {

    private final static String TAG = "SeekBarView";
    private static final int RUN = 0x01;

    private TextView mTvStartTime;
    private SeekBar mSeekBar;
    private TextView mTvEndTime;

    //测试数据 总时长 : 单位:毫秒
    private static long TEST_TOTAL_DURATION = (long) (0.2 * 60 * 1000);

    private long currentPlayPos = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == RUN){
                //开始后, 开始累加, 毫秒累加
                currentPlayPos += 1000;
                Log.d(TAG, "handleMessage: -->"+currentPlayPos);

                long currentPos = setProgress();
                msg = obtainMessage(RUN);
                //算出刻度, (currentPos%1000)可用于控制倍率 速率 , 正常都是整除的
                sendMessageDelayed(msg, 1000 - (currentPos%1000));
            }
        }
    };

    private long setProgress() {


        long currentPlayMills = currentPlayPos;

        mTvStartTime.setText(stringForTime(currentPlayMills));

        long totalMills = TEST_TOTAL_DURATION;
        int currentSeekBarPos = (int) (100f*currentPlayMills/totalMills);
//        Log.d(TAG, "currentSeekBarPos: "+currentSeekBarPos);
        //当前位置
        mSeekBar.setProgress(currentSeekBarPos);

       return currentPlayMills;
    }

    public SeekBarView(Context context) {
        this(context, null);
    }

    public SeekBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void assignViews(View view) {
        mTvStartTime = view.findViewById(R.id.tv_StartTime);
        mSeekBar = view.findViewById(R.id.seekBar);
        mTvEndTime = view.findViewById(R.id.tv_EndTime);

        mTvStartTime.setText("--:--:--");
        mTvEndTime.setText("--:--:--");

        initTest();

    }

    private void initTest() {
        //初始化测试数据
        mTvEndTime.setText(stringForTime(TEST_TOTAL_DURATION));
        mTvStartTime.setText(stringForTime(0));
        mHandler.sendEmptyMessage(RUN);
    }


    private String stringForTime(long position) {


        int totalSeconds = (int) ((position / 1000.0));

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        Log.d(TAG, "stringForTime: "+String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds));
        return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
    }


    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.seekbar_view, null);
        addView(rootView);
        assignViews(rootView);
        initEvent();

    }

    private void initEvent() {

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: " + progress + "->" + fromUser);

                if (fromUser){
                    float currentPercent = progress/100f;
                    mTvStartTime.setText(stringForTime((long) (TEST_TOTAL_DURATION*currentPercent)));
                }

                if (progress == 100){
                    Log.d(TAG, "onProgressChanged: 播放完毕, 移除");
                    mHandler.removeCallbacksAndMessages(null);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //开始拖动
                Log.d(TAG, "onStartTrackingTouch: " + seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //停止拖动
                Log.d(TAG, "onStopTrackingTouch: ");
            }
        });
    }


    public void stop() {
        mHandler.removeCallbacksAndMessages(null);
    }
}
