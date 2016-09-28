package com.fire.demo.rxjavaretrofit2mvp.mvp.view;

import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;

/**
 * Created by pc on 2016/9/26.
 */
public interface IUserLoginView {
    void loginSuccess();

    void loginFail(int toast, String reason);

    void showLoading();

    void hideLoading();

    void saveCommonUserInfo(UserInfo info);
}
