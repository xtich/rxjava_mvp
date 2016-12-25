package com.customeview.rxjava_mvp.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class MessageTypeItemBean implements Serializable {
    /**
     * content :
     * createddate :
     * dtype : MessageTypeBean
     * id : BuildingWaring
     * name : 警告
     * scount : 0
     * sortno : 6
     * valid : 1
     * version : 0
     */

    @SerializedName("content")
    public String content;
    @SerializedName("createddate")
    public String createddate;
    @SerializedName("dtype")
    public String dtype;
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("scount")
    public String scount;
    @SerializedName("sortno")
    public String sortno;
    @SerializedName("valid")
    public int valid;
    @SerializedName("version")
    public int version;

    /**
     * content :
     * createddate :
     * id : BuildingNotify
     * name : 通知
     * scount : 11
     * sortno : 1
     * valid : 1
     * version : 0
     */

    @Override
    public String toString() {
        return "MessageTypeItemBean{" +
                "content='" + content + '\'' +
                ", createddate='" + createddate + '\'' +
                ", dtype='" + dtype + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", scount=" + scount +
                ", sortno='" + sortno + '\'' +
                ", valid=" + valid +
                ", version=" + version +
                '}';
    }
}
