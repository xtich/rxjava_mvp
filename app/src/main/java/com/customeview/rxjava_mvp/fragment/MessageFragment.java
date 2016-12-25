package com.customeview.rxjava_mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.customeview.rxjava_mvp.R;
import com.customeview.rxjava_mvp.adapter.MessageListAdapter;
import com.customeview.rxjava_mvp.bean.MessageTypeItemBean;
import com.customeview.rxjava_mvp.confing.Config;
import com.customeview.rxjava_mvp.fragment.fragmentImpl.IMessageFragmentImpl;
import com.customeview.rxjava_mvp.persenter.persneterImpl.MessagePersenterImpl;
import com.customeview.rxjava_mvp.utils.SnackBarCallBack;
import com.customeview.rxjava_mvp.utils.SnackBarUtils;
import com.customeview.rxjava_mvp.view.GridItemDividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by gvesryb on 2016/12/23.
 */

public class MessageFragment extends BaseFragment implements IMessageFragmentImpl {

    @InjectView(R.id.msg_recy)
    RecyclerView msgRecy;
    @InjectView(R.id.prograss)
    ProgressBar prograss;
    private MessagePersenterImpl messagePersenter;
    private MessageListAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_frag, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        msgRecy.setLayoutManager(mLinearLayoutManager);
        msgRecy.setHasFixedSize(true);
        msgRecy.addItemDecoration(new GridItemDividerDecoration(getContext(), R.dimen.divider_height, R.color.divider));
        msgRecy.setItemAnimator(new DefaultItemAnimator());
        msgRecy.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        if (adapter.getItemCount()>0){
            adapter.clearData();
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("token",Config.TOKEN);
        messagePersenter.getMessageListData( map);
    }

    /***
     * 获取网络数据
     */
    private void initData() {
        messagePersenter = new MessagePersenterImpl(getContext(), this);
        adapter = new MessageListAdapter(R.layout.message_adapter_layout, getContext());

    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    public static MessageFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("MESSAGE", content);
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showProgressDialog() {
        if (prograss!=null){
            prograss.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void hidProgressDialog() {
        if (prograss!=null){
            prograss.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError(String error) {
        if (msgRecy!=null){
            SnackBarUtils.ActionSnackBarShow(getActivity(), getString(R.string.network_error), getString(R.string.again), new SnackBarCallBack() {
                @Override
                public void callBack() {

                }
            });
        }

    }

    @Override
    public void upDataMessageList(ArrayList<MessageTypeItemBean> msgList) {
        adapter.addAdapterDatas(msgList);
    }
}
