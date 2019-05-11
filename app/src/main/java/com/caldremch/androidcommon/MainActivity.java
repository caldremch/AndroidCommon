package com.caldremch.androidcommon;

import android.view.View;

import com.caldremch.common.base.BaseLoadingActivity;

public class MainActivity extends BaseLoadingActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main_with_diy_view;
    }

    @Override
    public void initView() {

    }

    public void open(View view) {

        BuyDialog dialog = new BuyDialog(mContext);
        dialog.show();

    }
}
