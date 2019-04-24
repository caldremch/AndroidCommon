package com.caldremch.common.base;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.caldremch.common.R;
import com.caldremch.common.utils.MetricsUtils;
import com.caldremch.common.widget.EasyTitleBar;
import com.caldremch.common.widget.MultiStateView;

/**
 * @author Caldremch
 * @date 2019-04-24 11:13
 * @email caldremch@163.com
 * @describe
 **/
public class BaseLoadingActivity extends BaseActivity {


    private MultiStateView mStateView;
    private ConstraintLayout mRootLayout;

    /**
     * 在BaseLoadingActivity子类中使用已经失效,无用设置
     *
     * @return
     */
    @Deprecated
    @Override
    public final int getLayoutId() {
        return 0;
    }

    protected int getContentViewId() {
        return 0;
    }

    @Override
    public View getLayoutView() {


        mRootLayout = new ConstraintLayout(mContext);

        //加载View 要在 TitleView 的底下, 不能遮拦 TitleView
        //1.默认设置一个 TitleView,
        //2.使用自定义的 TitleView
        //3.规定开发在布局中使用固定的 id 作为 TitleView 的 id

        //加载
        if (getContentViewId() != 0) {
            ViewGroup childRootView = (ViewGroup) LayoutInflater.from(mContext).inflate(getContentViewId(), null);
            if (childRootView != null) {

                View titleView = childRootView.findViewById(R.id.android_common_title_view_id);

                if (titleView == null) {

                    if (getTitleViewId() == 0) {
                        //默认 titleView
                        EasyTitleBar defaultTitleView = new EasyTitleBar(mContext);
                        mRootLayout.addView(defaultTitleView);
                        ConstraintLayout.LayoutParams titleParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        titleParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                        titleParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
                        titleParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                        defaultTitleView.setLayoutParams(titleParams);
                        defaultTitleView.setTitle("默认标题");
                        defaultTitleView.setId(R.id.default_android_common_title_view_id);
                        initContentView(mRootLayout, childRootView, R.id.default_android_common_title_view_id);
                    } else {
                        //自定义 titleView
                        titleView = LayoutInflater.from(mContext).inflate(getTitleViewId(), mRootLayout, false);
                        ConstraintLayout.LayoutParams titleParams = (ConstraintLayout.LayoutParams) titleView.getLayoutParams();
                        //内部做适配处理, 根据自己项目定制
                        titleView.setPadding(0, MetricsUtils.getStatusBarHeight(mContext), 0, 0);
                        mRootLayout.addView(titleView);
                        titleParams.width = 0;
                        titleParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                        titleParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
                        titleParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                        titleView.setLayoutParams(titleParams);
                        if (titleView.getId() == View.NO_ID) {
                            titleView.setId(R.id.default_android_common_title_view_id);
                        }
                        initContentView(mRootLayout, childRootView, titleView.getId());

                    }
                } else {

                    // TODO: 2019-04-24 此种情况下, childRootView的跟布局必须是ConstraintLayout, 后期再优化 , 如何解决这种情况
                    mRootLayout.addView(childRootView);

                    //建立一个空的 View
                    View placeHolderView = new TextView(mContext);
                    placeHolderView.setBackgroundColor(Color.TRANSPARENT);
                    mStateView = new MultiStateView(mContext);
                    mStateView.addView(placeHolderView);

                    //设置 ChildView 的布局参数
                    ConstraintLayout.LayoutParams childRootViewLayoutParams = new ConstraintLayout.LayoutParams(0,0);
                    childRootViewLayoutParams.topToTop =ConstraintLayout.LayoutParams.PARENT_ID;
                    childRootViewLayoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                    childRootViewLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
                    childRootViewLayoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
                    childRootView.setLayoutParams(childRootViewLayoutParams);
                    childRootView.addView(mStateView);

                    //设置 stateView 的布局参数
                    ConstraintLayout.LayoutParams stateViewLayoutParams = new ConstraintLayout.LayoutParams(0,0);
                    stateViewLayoutParams.topToBottom =R.id.android_common_title_view_id;
                    stateViewLayoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                    stateViewLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
                    stateViewLayoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
                    mStateView.setLayoutParams(stateViewLayoutParams);

                    mStateView.setViewForState(R.layout.common_layout_status_empty, MultiStateView.VIEW_STATE_EMPTY);
                    mStateView.setViewForState(R.layout.common_layout_status_loading, MultiStateView.VIEW_STATE_LOADING);
                    mStateView.setViewForState(R.layout.common_layout_status_error, MultiStateView.VIEW_STATE_ERROR);

                    //设置 ContentView
                    FrameLayout.LayoutParams placeHolderViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                    placeHolderView.setLayoutParams(placeHolderViewParams);
                }


            }
        }else{


            TextView noLayoutHolder = new TextView(mContext);
            ConstraintLayout.LayoutParams childRootViewLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            childRootViewLayoutParams.topToTop =ConstraintLayout.LayoutParams.PARENT_ID;
            childRootViewLayoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            childRootViewLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            childRootViewLayoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            noLayoutHolder.setLayoutParams(childRootViewLayoutParams);
            noLayoutHolder.setPadding(40, 40, 40, 40);
            noLayoutHolder.setText("due, just override the getContentViewId() and do something, ok?");
            noLayoutHolder.setGravity(Gravity.CENTER);

            mRootLayout.addView(noLayoutHolder);


        }

        return mRootLayout;
    }


    /**
     * @param rootLayout    根布局
     * @param childRootView 内容布局
     * @param id            内容布局要在哪个布局的底部
     */
    private void initContentView(ConstraintLayout rootLayout, View childRootView, int id) {

        mStateView = new MultiStateView(mContext);
        mStateView.addView(childRootView);
        rootLayout.addView(mStateView);

        mStateView.setViewForState(R.layout.common_layout_status_empty, MultiStateView.VIEW_STATE_EMPTY);
        mStateView.setViewForState(R.layout.common_layout_status_loading, MultiStateView.VIEW_STATE_LOADING);
        mStateView.setViewForState(R.layout.common_layout_status_error, MultiStateView.VIEW_STATE_ERROR);

        ConstraintLayout.LayoutParams stateViewLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.PARENT_ID, ConstraintLayout.LayoutParams.PARENT_ID);
        mStateView.setLayoutParams(stateViewLayoutParams);
        if (id == ConstraintLayout.LayoutParams.PARENT_ID) {
            stateViewLayoutParams.topToTop = id;
        } else {
            stateViewLayoutParams.topToBottom = id;
        }
        stateViewLayoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        stateViewLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        stateViewLayoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;

        //设置 ContentView
        FrameLayout.LayoutParams childRootViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        childRootView.setLayoutParams(childRootViewParams);


    }

    protected final void onEmptyStatus() {
        if (mStateView != null) {
            mStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        }
    }

    protected final void onErrorStatus() {
        if (mStateView != null) {
            mStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
    }

    protected final void onContentStatus() {
        if (mStateView != null) {
            mStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        }
    }

    protected final void onLoadingStatus() {
        if (mStateView != null) {
            mStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        }
    }

    public int getTitleViewId() {
        return 0;
    }
}
