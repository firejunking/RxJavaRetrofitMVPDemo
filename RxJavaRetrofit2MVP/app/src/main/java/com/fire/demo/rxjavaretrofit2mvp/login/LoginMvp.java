package com.fire.demo.rxjavaretrofit2mvp.login;

import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;
import com.fire.demo.rxjavaretrofit2mvp.login.listener.OnLoginListener;

import java.util.Map;

import rx.Subscription;

/**
 * @author V.
 * @created on: 2018/10/25 下午3:07
 * @description
 * @remark
 */
public interface LoginMvp {
    interface IUserLoginView {
        void loginSuccess();

        void loginFail(int toast, String reason);

        void showLoading();

        void hideLoading();

        void saveCommonUserInfo(UserInfo info);
    }

    interface IUserLoginModel {
        Subscription login(Map<String, String> maps, OnLoginListener listener);
    }

    interface IUserLoginPresenter {
        void login(Map<String, String> maps);
    }
}
