package com.fire.demo.rxjavaretrofit2mvp.component;

import android.os.Bundle;


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
