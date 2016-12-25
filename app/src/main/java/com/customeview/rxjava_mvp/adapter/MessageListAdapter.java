package com.customeview.rxjava_mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.customeview.rxjava_mvp.R;
import com.customeview.rxjava_mvp.bean.MessageTypeBean;
import com.customeview.rxjava_mvp.bean.MessageTypeItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/25 0025.
 */

public class MessageListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder >{
    private final Context context;
    private final int id;
    public List<MessageTypeItemBean> list=new ArrayList<>();

    public MessageListAdapter(int id, Context context) {
        this.id=id;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(id,null);
        return new  mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mViewHolder mViewHolder= (mViewHolder) holder;
        MessageTypeItemBean bean=list.get(position);
        if (bean==null){
            return;
        }
        mViewHolder.tv_number.setText(bean.scount);
        mViewHolder.tv_date.setText(bean.createddate);
        mViewHolder.tv_content.setText(bean.content);
        mViewHolder.tv_title.setText(bean.name);


    }

    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }

    protected  class  mViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title,tv_content,tv_date,tv_number;
        public mViewHolder(View itemView) {
            super(itemView);
            tv_title= (TextView) itemView.findViewById(R.id.message_tv_title);
            tv_content= (TextView) itemView.findViewById(R.id.message_tv_content);
            tv_date= (TextView) itemView.findViewById(R.id.message_tv_time);
            tv_number= (TextView) itemView.findViewById(R.id.message_tv_number);
        }
    }
    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
    public void addAdapterDatas(List<MessageTypeItemBean> ls){
        list.addAll(ls);
        notifyDataSetChanged();
    }
}
