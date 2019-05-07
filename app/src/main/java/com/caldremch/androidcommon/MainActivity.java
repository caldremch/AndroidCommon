package com.caldremch.androidcommon;

import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.caldremch.common.base.BaseLoadingActivity;
import com.caldremch.common.widget.MessageCountView;

public class MainActivity extends BaseLoadingActivity {


    public String i1 = "http://qiniu.jpark.vip/jpark/img/classifyImg-15560783101811.gif?imageView2/2/h/336";
    public String i2 = "http://dotnettest.jpark.vip/4d283363425c4481a3005675a0991a2c?imageView2/2/h/336";
    public String i3 = "http://dotnettest.jpark.vip/99e42807ca514a27b84ce49ce1b3adb2?imageView2/2/h/336";
    public String i4 = "http://qiniu.jpark.vip/jpark/img/classifyImg-15562679563610023H2OEzy7cwNaaXPLa5&690.jpg?imageView2/2/h/336";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main_with_diy_view;
    }

    private MessageCountView tv;
    private MessageCountView tv2;
    private MessageCountView tv3;
    private ImageView iv;

    private void assignViews() {
        tv = (MessageCountView) findViewById(R.id.tv);
        tv2 = (MessageCountView) findViewById(R.id.tv2);
        tv3 = (MessageCountView) findViewById(R.id.tv3);
        iv = findViewById(R.id.iv);
    }


    //
    @Override
    public void initView() {
        startActivity(new Intent(this, ClBarrierActivity.class));
        assignViews();
        tv.setMsgCount("1");
        tv2.setMsgCount("67");
        tv3.setMsgCount("99+");

        Glide.with(this).asBitmap().load(i3).into(iv);
    }
//
//    @Override
//    public int getTitleViewId() {
//        return R.layout.my_self_title_view;
//    }
}
