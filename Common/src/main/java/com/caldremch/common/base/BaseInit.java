package com.caldremch.common.base;

import androidx.annotation.LayoutRes;

/**
 * @author Caldremch
 * @date 2019/1/24
 * @Email caldremch@163.com
 * @describe
 **/
public interface BaseInit {

    @LayoutRes
    int getLayoutId();

    default void initView() {
    }

    default void initData() {
    }

    default void initEvent() {
    }


}
