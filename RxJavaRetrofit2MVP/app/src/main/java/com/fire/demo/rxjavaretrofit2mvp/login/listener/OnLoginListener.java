package com.fire.demo.rxjavaretrofit2mvp.login.listener;

import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;

/**
 * @author V.
 * @created on: 2018/10/25 下午3:29
 * @description
 * @remark
 */
public interface OnLoginListener {
    void loginSuccess(UserInfo userInfo);

    void loginFailed(int toast, String reason);

    void requestCompleted();
}
