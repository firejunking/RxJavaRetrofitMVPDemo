package com.fire.demo.rxjavaretrofit2mvp.net.util;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.fire.demo.rxjavaretrofit2mvp.R;
import com.fire.demo.rxjavaretrofit2mvp.net.exception.ResultException;
import com.fire.demo.rxjavaretrofit2mvp.widget.DialogUtils;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by pc on 2016/8/25.
 */
public class ProgressSubscriber<T> extends Subscriber<T> {
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private String textResources;
    private Context context;

    public ProgressSubscriber(Context context, String textResources, SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.textResources = textResources;
        this.context = context;

    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        super.onStart();
        if (!textResources.isEmpty())
            DialogUtils.getInstance().popRemindDialog(context, textResources);

    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if (!textResources.isEmpty())
            DialogUtils.getInstance().disMissRemind();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Log.i("GWTKey", "请求异常为" + e.getClass());
        if (!textResources.isEmpty())
            DialogUtils.getInstance().disMissRemind();
        //请求异常提示
        String toast = "";
        if (e instanceof UnknownHostException)
            toast = context.getString(R.string.network_connection_faile);
        else if (e instanceof SocketTimeoutException)
            toast = context.getString(R.string.network_connection_time_out);
        else if (e instanceof ConnectException)
            toast = context.getString(R.string.network_connection_time_out);
        else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int responseCode = httpException.code();
            if (responseCode >= 400 && responseCode <= 417) {
                toast = context.getString(R.string.common_url_error);
            } else if (responseCode >= 500 && responseCode <= 505) {
                toast = context.getString(R.string.network_connection_busy);
            } else {
                toast = context.getString(R.string.network_connection_exception);
            }
        } else if (e instanceof ResultException) {
            ResultException resultException = (ResultException) e;
            int resultCode = resultException.getErrCode();
            if (resultCode == -1) {
                //token失效
            } else if (resultCode <= 0) {
                toast = resultException.getMessage();
            } else {
                //正常不会进到这里
                toast = context.getString(R.string.common_data_exception);
            }
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof NullPointerException)
            toast = context.getString(R.string.common_data_exception);
        else
            toast = context.getString(R.string.common_unknown_error);

        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }


}
