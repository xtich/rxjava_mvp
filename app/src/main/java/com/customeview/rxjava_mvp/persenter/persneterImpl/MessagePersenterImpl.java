package com.customeview.rxjava_mvp.persenter.persneterImpl;

import android.content.Context;
import android.util.Log;

import com.customeview.rxjava_mvp.api.ApiManager;
import com.customeview.rxjava_mvp.bean.MessageTypeBean;
import com.customeview.rxjava_mvp.confing.Config;
import com.customeview.rxjava_mvp.fragment.MessageFragment;
import com.customeview.rxjava_mvp.persenter.MessagePersenter;
import com.customeview.rxjava_mvp.utils.CacheUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class MessagePersenterImpl extends BasePersenterImpl implements MessagePersenter {

    private final MessageFragment messageFragment;
    private CacheUtil cacheUtil;
    private Gson gson=new Gson();

    public MessagePersenterImpl(Context  context, MessageFragment messageFragment) {
        this.messageFragment=messageFragment;
        cacheUtil=CacheUtil.get(context);

    }
    @Override
    public void getMessageListData(HashMap<String,String> map) {
        Subscription subscription= ApiManager.getInstance().getMessageService().getMessageList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageTypeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        messageFragment.hidProgressDialog();
                        messageFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(MessageTypeBean messageTypeBean) {
                        Log.e("dddd",messageTypeBean.data.toString());
                        messageFragment.hidProgressDialog();
                        cacheUtil.put(Config.MESSAGE, gson.toJson(messageTypeBean));
                        messageFragment.upDataMessageList(messageTypeBean.data);
                    }
                });
        addSubcrible(subscription);
    };


}
