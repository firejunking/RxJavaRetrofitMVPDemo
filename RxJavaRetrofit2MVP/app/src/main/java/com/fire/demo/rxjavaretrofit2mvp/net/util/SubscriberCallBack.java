package com.fire.demo.rxjavaretrofit2mvp.net.util;

import android.net.ParseException;
import android.util.Log;

import com.fire.demo.rxjavaretrofit2mvp.R;
import com.fire.demo.rxjavaretrofit2mvp.net.exception.ResultException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by pc on 2016/8/31.
 */
public class SubscriberCallBack<T> extends Subscriber<T> {
    private ApiCallback<T> apiCallback;

    public SubscriberCallBack(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        Log.i("MVPDemo", "请求异常为" + e.getClass());
        //请求异常提示
        int toast = 0;
        String reason = "";
        if (e instanceof UnknownHostException)
            toast = R.string.network_connection_faile;
        else if (e instanceof SocketTimeoutException)
            toast = R.string.network_connection_time_out;
        else if (e instanceof ConnectException)
            toast = R.string.network_connection_time_out;
        else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int responseCode = httpException.code();
            if (responseCode >= 400 && responseCode <= 417) {
                toast = R.string.common_url_error;
            } else if (responseCode >= 500 && responseCode <= 505) {
                toast = R.string.network_connection_busy;
            } else {
                toast = R.string.network_connection_exception;
            }
        } else if (e instanceof ResultException) {
            ResultException resultException = (ResultException) e;
            int resultCode = resultException.getErrCode();
            if (resultCode == -1) {
//                GwtKeyApp.getInstance().doReLogin();
            } else if (resultCode <= 0) {
                reason = resultException.getMessage();
            } else {
                //正常不会进到这里
                toast = R.string.common_data_exception;
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof NullPointerException)
            toast = R.string.common_data_exception;
        else
            toast = R.string.common_unknown_error;

        apiCallback.onFailure(toast, reason);
        apiCallback.onCompleted();
    }

    @Override
    public void onNext(T t) {
        apiCallback.onSuccess(t);
    }
}
