package com.customeview.rxjava_mvp.persenter.persneterImpl;

import com.customeview.rxjava_mvp.persenter.BasePersenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class BasePersenterImpl implements BasePersenter {

    private CompositeSubscription mCompositeSubscription;

    protected void addSubcrible(Subscription s){
        if (this.mCompositeSubscription==null){
            this.mCompositeSubscription=new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);

    }
    @Override
    public void unSubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
