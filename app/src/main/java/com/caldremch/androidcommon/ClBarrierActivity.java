package com.caldremch.androidcommon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.caldremch.common.utils.DensityUtil;

/**
 * @author Caldremch
 * @date 2019-04-27 22:55
 * @email caldremch@163.com
 * @describe
 **/
public class ClBarrierActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl_barrier);

        imageView = findViewById(R.id.iv);



        RequestOptions options = new RequestOptions();
        Glide.with(this)
                .asBitmap()
                .load("http://qiniu.jpark.vip/jpark/img/1555654901332WechatIMG10.png")
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d("DDDDDDDD", "width = " + resource.getWidth() + ",height =" + resource.getHeight());

                        float w = resource.getWidth();
                        float h = resource.getHeight();
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                        float ratio = (float) imageView.getWidth() / (float) w;
                        params.height = (int) (resource.getHeight() * ratio);
                        imageView.setLayoutParams(params);
                        return false;
                    }
                }).into(imageView);
//                .into(new Target<Bitmap>() {
//                    @Override
//                    public void onLoadStarted(@Nullable Drawable placeholder) {
//                        Log.d("DDDDDDDD","onLoadStarted");
//                    }
//
//                    @Override
//                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                        Log.d("DDDDDDDD","onLoadFailed");
//                    }
//
//
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        Log.d("DDDDDDDD", "width = " + resource.getWidth() + ",height =" + resource.getHeight());
//                        imageView.setImageBitmap(resource);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//                        Log.d("DDDDDDDD","onLoadFailed");
//
//                    }
//
//                    @Override
//                    public void getSize(@NonNull SizeReadyCallback cb) {
//                    }
//
//                    @Override
//                    public void removeCallback(@NonNull SizeReadyCallback cb) {
//
//                    }
//
//                    @Override
//                    public void setRequest(@Nullable Request request) {
//
//                    }
//
//                    @Nullable
//                    @Override
//                    public Request getRequest() {
//                        return null;
//                    }
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onStop() {
//
//                    }
//
//                    @Override
//                    public void onDestroy() {
//
//                    }
//                });

    }
}
