package com.fire.demo.rxjavaretrofit2mvp.data.util;

import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;

/**
 * Created by pc on 2016/9/26.
 */
public class JsonHandleAdapter {
    public static UserInfo getUserLoginInfo(String jsonString) {
        //根据自己公司的业务编写
        return new UserInfo();
    }
}
