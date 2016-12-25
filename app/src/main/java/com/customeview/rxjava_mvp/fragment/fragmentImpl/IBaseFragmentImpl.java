package com.customeview.rxjava_mvp.fragment.fragmentImpl;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public interface IBaseFragmentImpl  {
    void showProgressDialog();

    void hidProgressDialog();

    void showError(String error);
}
