package com.fire.demo.rxjavaretrofit2mvp.mvp.base;

import android.os.Bundle;

import com.fire.demo.rxjavaretrofit2mvp.ui.base.BaseActivity;


/**
 * Created by pc on 2016/8/31.
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
