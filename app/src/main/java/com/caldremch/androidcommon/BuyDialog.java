package com.caldremch.androidcommon;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatDialog;

import com.caldremch.common.utils.DensityUtil;
import com.caldremch.common.utils.MetricsUtils;

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



        layout.width = WindowManager.LayoutParams.MATCH_PARENT;
        layout.height =  WindowManager.LayoutParams.WRAP_CONTENT;
        layout.gravity = Gravity.BOTTOM;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layout);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        init(context, style);
        setContentView(R.layout.bug_dialog2);
        ((FrameLayout)getWindow().getDecorView()).setClipChildren(false);
    }

    private void init(Context context, int style) {


    }


}
