package com.fire.demo.rxjavaretrofit2mvp.login.model;

import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;
import com.fire.demo.rxjavaretrofit2mvp.data.util.JsonHandleAdapter;
import com.fire.demo.rxjavaretrofit2mvp.login.LoginMvp;
import com.fire.demo.rxjavaretrofit2mvp.login.listener.OnLoginListener;
import com.fire.demo.rxjavaretrofit2mvp.net.manager.RetrofitManage;
import com.fire.demo.rxjavaretrofit2mvp.net.util.ApiCallback;
import com.fire.demo.rxjavaretrofit2mvp.net.util.SubscriberCallBack;

import org.json.JSONObject;

import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2016/9/26.
 */
public class UserLoginModel implements LoginMvp.IUserLoginModel {

    @Override
    public Subscription login(Map<String, String> maps, final OnLoginListener listener) {
        Observable<JSONObject> observable = RetrofitManage.getInstance().getHttpServiceConnection().login(maps);
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SubscriberCallBack<>(new ApiCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject model) {
                        UserInfo userInfo = JsonHandleAdapter.getUserLoginInfo(model.toString());
                        listener.loginSuccess(userInfo);
                    }

                    @Override
                    public void onFailure(int msg, String reason) {
                        listener.loginFailed(msg, reason);
                    }

                    @Override
                    public void onCompleted() {
                        listener.requestCompleted();
                    }
                }));
    }
}
