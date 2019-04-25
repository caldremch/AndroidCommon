package com.caldremch.androidcommon;

import com.caldremch.common.base.BaseLoadingActivity;
import com.caldremch.common.widget.EasyTitleBar;
import com.caldremch.common.widget.MessageCountView;

public class MainActivity extends BaseLoadingActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main_with_diy_view;
    }

    private MessageCountView tv;
    private MessageCountView tv2;
    private MessageCountView tv3;

    private void assignViews() {
        tv = (MessageCountView) findViewById(R.id.tv);
        tv2 = (MessageCountView) findViewById(R.id.tv2);
        tv3 = (MessageCountView) findViewById(R.id.tv3);
    }


    //
    @Override
    public void initView() {
        assignViews();
        tv.setMsgCount("1");
        tv2.setMsgCount("67");
        tv3.setMsgCount("99+");

    }
//
//    @Override
//    public int getTitleViewId() {
//        return R.layout.my_self_title_view;
//    }
}
