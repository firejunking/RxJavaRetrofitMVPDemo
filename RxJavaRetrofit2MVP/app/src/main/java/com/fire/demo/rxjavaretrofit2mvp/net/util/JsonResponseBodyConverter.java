package com.fire.demo.rxjavaretrofit2mvp.net.util;

import com.fire.demo.rxjavaretrofit2mvp.net.base.BaseResultData;
import com.fire.demo.rxjavaretrofit2mvp.net.exception.ResultException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by pc on 2016/8/25.
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    public JsonResponseBodyConverter() {

    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JSONObject jsonObject;
        String response = value.string();
        try {
            BaseResultData bean = new BaseResultData(response);
            //这里判断resultCode的值
            if (Integer.parseInt(bean.getResultCode()) >= 0) {
                jsonObject = new JSONObject(response);
                return (T) jsonObject;
            } else {
                //token失效-->resultCode==-1
                //或者其他错误
                throw new ResultException(Integer.parseInt(bean.getResultCode()), bean.getResultDesc());
            }
        } catch (JSONException e) {
            return null;
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }
}
