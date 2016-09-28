package com.fire.demo.rxjavaretrofit2mvp.net.service;

import com.fire.demo.rxjavaretrofit2mvp.data.URLs;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by pc on 2016/8/23.
 */
public interface HttpService {

    //检查版本更新接口
    @FormUrlEncoded
    @POST(URLs.CheckVersion)
    Observable<JSONObject> checkVersion(@FieldMap() Map<String, String> maps);

    //用户登录接口
    @FormUrlEncoded
    @POST(URLs.Login)
    Observable<JSONObject> login(@FieldMap() Map<String, String> maps);


}
