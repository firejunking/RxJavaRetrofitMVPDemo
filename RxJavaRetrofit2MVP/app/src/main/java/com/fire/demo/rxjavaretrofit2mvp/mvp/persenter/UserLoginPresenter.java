package com.fire.demo.rxjavaretrofit2mvp.mvp.persenter;

import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;
import com.fire.demo.rxjavaretrofit2mvp.mvp.base.BasePresenter;
import com.fire.demo.rxjavaretrofit2mvp.mvp.model.UserLoginModel;
import com.fire.demo.rxjavaretrofit2mvp.mvp.model.impl.IUserLoginModel;
import com.fire.demo.rxjavaretrofit2mvp.mvp.model.listener.OnLoginListener;
import com.fire.demo.rxjavaretrofit2mvp.mvp.view.IUserLoginView;

import java.util.Map;

/**
 * Created by pc on 2016/9/26.
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView> {
    private IUserLoginModel iUserLoginModel;

    public UserLoginPresenter(IUserLoginView view) {
        attachView(view);
        iUserLoginModel = new UserLoginModel();
    }

    public void login(Map<String, String> maps) {
        mvpView.showLoading();
        addSubscription(iUserLoginModel.login(maps, new OnLoginListener() {
            @Override
            public void loginSuccess(UserInfo userInfo) {
                mvpView.loginSuccess();
                if (null != userInfo)
                    mvpView.saveCommonUserInfo(userInfo);
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
