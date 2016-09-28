package com.fire.demo.rxjavaretrofit2mvp.mvp.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pc on 2016/8/31.
 */
public class BasePresenter<V> implements Presenter<V> {
    public V mvpView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        this.mvpView = view;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
        onUnSubscribe();
    }

    //RxJava取消注册，以避免内存泄露
    public void onUnSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
}
