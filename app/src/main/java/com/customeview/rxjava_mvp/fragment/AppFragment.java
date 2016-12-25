package com.customeview.rxjava_mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customeview.rxjava_mvp.R;

import butterknife.ButterKnife;

/**
 * Created by gvesryb on 2016/12/23.
 */

public class AppFragment extends  BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_frag, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    public static AppFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("APP", content);
        AppFragment fragment = new AppFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
