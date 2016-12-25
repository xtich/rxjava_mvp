package com.customeview.rxjava_mvp.api.interf;


import com.customeview.rxjava_mvp.bean.MessageTypeBean;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by gvesryb on 2016/12/23.
 */

public interface Messages {
    @POST("api/MessageNotifyAction/findMessageCategory")
    Observable<MessageTypeBean> getMessageList(@QueryMap HashMap<String,String> paramsMap);

}
