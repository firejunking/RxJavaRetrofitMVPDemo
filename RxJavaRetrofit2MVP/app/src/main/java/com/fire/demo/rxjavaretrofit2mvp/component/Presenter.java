package com.fire.demo.rxjavaretrofit2mvp.component;

/**
 * Created by pc on 2016/8/31.
 */
public interface Presenter<V> {
    void attachView(V view);

    void detachView();
}
