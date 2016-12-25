package com.customeview.rxjava_mvp.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by gvesryb on 2016/12/23.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public BaseApplication() {
        instance=this;
    }
    public static BaseApplication getInstance(){
        if (instance==null){
            instance=new BaseApplication();
        }
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
