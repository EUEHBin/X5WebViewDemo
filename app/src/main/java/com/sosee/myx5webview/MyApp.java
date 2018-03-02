package com.sosee.myx5webview;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;

/**
 * author:EUEH(gubin)
 * csdn:http://blog.csdn.net/eueheuen
 * date:2018/3/1
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 x5内核
        new Thread(new Runnable() {
            @Override
            public void run() {
                QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
                    @Override
                    public void onCoreInitFinished() {

                    }
                    @Override
                    public void onViewInitFinished(boolean b) {

                    }
                };
                QbSdk.initX5Environment(getApplicationContext(),cb);

            }
        }).start();
    }
}
