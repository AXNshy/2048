package com.jxm.test;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jxm.udpmulticast.R;


import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/27.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    PtrFrameLayout mFrame;
    LinearLayout imageContent;
    TextView headHint;

    TextView cToJava;

    NDK ndk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PtrFrameLayout.DEBUG=true;
        setContentView(R.layout.pull_to_fresh);
        mFrame = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
        mFrame.setHeaderView(LayoutInflater.from(this).inflate(R.layout.pull_to_refresh_head,null));
        headHint = (TextView) mFrame.getHeaderView().findViewById(R.id.tv_ref);
        cToJava = (TextView) findViewById(R.id.tv_c_to_java);
        cToJava.setOnClickListener(this);
        ndk = new NDK(this);
        mFrame.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {
                headHint.setText("重置");
                Log.e("TAG","重置");
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
                headHint.setText("准备刷新");Log.e("TAG","准备刷新");
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {
                headHint.setText("开始刷新");Log.e("TAG","开始刷新");
            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                headHint.setText("刷新完成");Log.e("TAG","刷新完成");
            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//                headHint.setText("UIposition改变");Log.e("TAG","UIposition改变");
            }
        });
        mFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(ndk.getStringFromC());

                        System.out.println(ndk._sayHello());

                        mFrame.refreshComplete();
                    }
                }, 1800);
            }
        });
    }
    static {
        System.loadLibrary("ndkTest");
    }

    @Override
    public void onClick(View v) {
        ndk.onClick();

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });

        Subscriber sb = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e("TAG","Complete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e("TAG",integer.getClass().getName());
            }

        };
        observable.map(new Func1<String,Integer>() {

            @Override
            public Integer call(String s) {
                return Integer.parseInt(s);
            }
        }).subscribe(sb);
    }


}
