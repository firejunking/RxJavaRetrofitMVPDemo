package com.fire.demo.rxjavaretrofit2mvp.net.manager;

import android.util.Log;

import com.fire.demo.rxjavaretrofit2mvp.BuildConfig;
import com.fire.demo.rxjavaretrofit2mvp.data.URLs;
import com.fire.demo.rxjavaretrofit2mvp.net.factory.JsonConverterFactory;
import com.fire.demo.rxjavaretrofit2mvp.net.service.HttpService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2016/8/23.
 */
public class RetrofitManage {
    private final static String BASE_URL = URLs.API_HEAD;
    private static RetrofitManage mRetrofitManage;
    private Retrofit mRetrofit;
    private HttpService mHttpService;

    private RetrofitManage() {
    }

    public static RetrofitManage getInstance() {
        if (mRetrofitManage == null) {
            synchronized (RetrofitManage.class) {
                if (mRetrofitManage == null) {
                    mRetrofitManage = new RetrofitManage();
                }
            }
        }
        return mRetrofitManage;
    }

    /**
     * 获取retrofit对象
     */
    private Retrofit getRetrofit() {
        if (null == mRetrofit) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("网络请求", message);
                    }
                });
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(logging);
            }
            builder.connectTimeout(60, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(JsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public HttpService getHttpServiceConnection() {
        if (mHttpService == null) {
            mHttpService = getRetrofit().create(HttpService.class);
        }
        return mHttpService;
    }

    /**
     * 设置观察者
     *
     * @param o   被观察者
     * @param s   观察者
     * @param <T> 数据类型
     */
    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()) //在io线程中处理网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);//在主线程中处理数据
    }
}
