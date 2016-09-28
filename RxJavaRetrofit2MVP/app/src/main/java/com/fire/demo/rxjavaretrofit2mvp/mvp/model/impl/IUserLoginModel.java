package com.fire.demo.rxjavaretrofit2mvp.mvp.model.impl;

import com.fire.demo.rxjavaretrofit2mvp.mvp.model.listener.OnLoginListener;

import java.util.Map;

import rx.Subscription;

/**
 * Created by pc on 2016/8/31.
 */
public interface IUserLoginModel {
    Subscription login(Map<String, String> maps, OnLoginListener listener);
}
