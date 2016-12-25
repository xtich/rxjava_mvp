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

public class MeFagment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.me_frg,container,false);
        ButterKnife.inject(this,view);
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
    public static MeFagment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ME", content);
        MeFagment fragment = new MeFagment();
        fragment.setArguments(args);
        return fragment;
    }
}
