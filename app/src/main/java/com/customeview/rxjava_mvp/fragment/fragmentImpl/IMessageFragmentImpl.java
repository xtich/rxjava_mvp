package com.customeview.rxjava_mvp.fragment.fragmentImpl;

import com.customeview.rxjava_mvp.bean.MessageTypeBean;
import com.customeview.rxjava_mvp.bean.MessageTypeItemBean;
import com.customeview.rxjava_mvp.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public interface IMessageFragmentImpl extends  IBaseFragmentImpl {

    void upDataMessageList(ArrayList<MessageTypeItemBean> msgList);
}
