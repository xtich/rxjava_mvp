package com.customeview.rxjava_mvp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
public class MessageTypeBean extends BaseData {
    public ArrayList<MessageTypeItemBean> getMegList() {
        return data;
    }

    public void setMegList(ArrayList<MessageTypeItemBean> megList) {
        this.data = megList;
    }
    @SerializedName("data")
    public ArrayList<MessageTypeItemBean> data;
    @SerializedName("success")
    public String success;


}
