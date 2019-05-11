package com.caldremch.androidcommon;

import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

/**
 * @author Caldremch
 * @date 2019-05-10 17:38
 * @email caldremch@163.com
 * @describe
 **/
public class BuyDialog extends AppCompatDialog {
    public BuyDialog(Context context) {
        this(context, R.style.GoodDialog);
    }

    public BuyDialog(Context context, int style) {
        super(context, style);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layout.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layout);
        init(context, style);
        setContentView(R.layout.bug_dialog);
    }

    private void init(Context context, int style) {


    }


}
