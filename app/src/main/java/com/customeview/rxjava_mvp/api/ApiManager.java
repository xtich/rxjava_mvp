package com.customeview.rxjava_mvp.api;


import com.customeview.rxjava_mvp.api.interf.Messages;
import com.customeview.rxjava_mvp.base.BaseApplication;
import com.customeview.rxjava_mvp.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 网络管理类
 * Created by gvesryb on 2016/12/23.
 */

public class ApiManager {
    static ApiManager instance;
    private static Messages messages;
    public  static Object look=new Object();

    public static ApiManager getInstance() {
        synchronized (ApiManager.class) {
            if (instance == null) {
                instance = new ApiManager();
            }
        }
        return instance;
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(BaseApplication.getInstance().getBaseContext())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };
    private static File httpCacheDirectory = new File(BaseApplication.getInstance().getCacheDir(), "zhihuCache");
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(cache)
            .build();


    public  Messages getMessageService(){
        if (messages==null){
            synchronized (look){
                messages=new Retrofit.Builder().baseUrl(URLS.BASE_URL)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(Messages.class);
            }
        }
        return messages;
    }

}
