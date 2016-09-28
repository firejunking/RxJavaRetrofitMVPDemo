package com.fire.demo.rxjavaretrofit2mvp.mvp.model.listener;


import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;


/**
 * Created by pc on 2016/8/31.
 */
public interface OnLoginListener {
    void loginSuccess(UserInfo userInfo);

    void loginFailed(int toast, String reason);

    void requestCompleted();
}
