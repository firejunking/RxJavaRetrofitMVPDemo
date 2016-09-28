package com.fire.demo.rxjavaretrofit2mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fire.demo.rxjavaretrofit2mvp.R;
import com.fire.demo.rxjavaretrofit2mvp.mvp.model.listener.OnLoginListener;
import com.fire.demo.rxjavaretrofit2mvp.net.manager.RetrofitManage;
import com.fire.demo.rxjavaretrofit2mvp.net.util.ProgressSubscriber;
import com.fire.demo.rxjavaretrofit2mvp.net.util.SubscriberOnNextListener;
import com.fire.demo.rxjavaretrofit2mvp.ui.base.BaseActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by pc on 2016/9/27.
 */
public class LauncherActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intData(new HashMap<String, String>());
    }

    private void intData(Map<String, String> maps) {
        Observable<JSONObject> observable = RetrofitManage.getInstance().getHttpServiceConnection().checkVersion(maps);
        RetrofitManage.getInstance().toSubscribe(observable, new ProgressSubscriber<JSONObject>(LauncherActivity.this, "", new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {

            }
        }));
    }
}
