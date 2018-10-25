package com.fire.demo.rxjavaretrofit2mvp.login.presenter;

import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;
import com.fire.demo.rxjavaretrofit2mvp.component.BasePresenter;
import com.fire.demo.rxjavaretrofit2mvp.login.LoginMvp;
import com.fire.demo.rxjavaretrofit2mvp.login.listener.OnLoginListener;
import com.fire.demo.rxjavaretrofit2mvp.login.model.UserLoginModel;

import java.util.Map;

/**
 * Created by pc on 2016/9/26.
 */
public class UserLoginPresenter extends BasePresenter<LoginMvp.IUserLoginView> implements LoginMvp.IUserLoginPresenter {
    private LoginMvp.IUserLoginModel iUserLoginModel;

    public UserLoginPresenter(LoginMvp.IUserLoginView view) {
        attachView(view);
        iUserLoginModel = new UserLoginModel();
    }

    @Override
    public void login(Map<String, String> maps) {
        mvpView.showLoading();
        addSubscription(iUserLoginModel.login(maps, new OnLoginListener() {
            @Override
            public void loginSuccess(UserInfo userInfo) {
                mvpView.loginSuccess();
                if (null != userInfo) {
                    mvpView.saveCommonUserInfo(userInfo);
                }
            }

            @Override
            public void loginFailed(int toast, String reason) {
                mvpView.loginFail(toast, reason);
            }

            @Override
            public void requestCompleted() {
                mvpView.hideLoading();
            }
        }));
    }
}
