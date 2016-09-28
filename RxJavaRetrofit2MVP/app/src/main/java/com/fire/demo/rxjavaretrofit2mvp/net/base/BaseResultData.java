package com.fire.demo.rxjavaretrofit2mvp.net.base;

import com.fire.demo.rxjavaretrofit2mvp.net.base.BaseResultBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by pc on 2016/8/4.
 */
public class
BaseResultData {

    private String resultDesc;
    private String resultCode;
    private JsonElement resultData;
    private static final String RESULTCODE = "resultCode";
    private static final String RESULTDESC = "resultDesc";
    private static final String RESULTDATA = "resultData";

    public BaseResultData(String jsonString) {
        if (jsonString.isEmpty())
            return;
        BaseResultBean baseResultData = getGson().fromJson(jsonString, BaseResultBean.class);
        JsonObject jsonObject = baseResultData.getResponse().getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> set = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : set) {
            String key = entry.getKey();
            if (key.equals(RESULTCODE)) {
                resultCode = entry.getValue().getAsString();
            } else if (key.equals(RESULTDESC)) {
                resultDesc = entry.getValue().getAsString();
            } else if (key.equals(RESULTDATA)) {
                resultData = entry.getValue();
            }
        }
    }

    public <T> T getResultData(Class<?> clazz) {
        if (null == getResultData() || getResultData().equals(""))
            return null;
        try {
            return (T) getGson().fromJson(getResultData(), clazz);
        } catch (JsonParseException e) {
            return null;
        }
    }


    public <T> T getResultData(String name, Class<?> clazz) {
        if (null == getResultData())
            return null;
        try {
            JsonObject jo = getResultData().getAsJsonObject();
            return (T) getGson().fromJson(jo.get(name), clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T getResultData(Type listType) {
        if (null == getResultData())
            return null;

        try {
            String json = getGson().toJson(getResultData());
            return getGson().fromJson(json, listType);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T getResultData(String name, Type listType) {
        if (null == getResultData())
            return null;

        try {
            String json = "";
            Set<Map.Entry<String, JsonElement>> set = getResultData().getAsJsonObject().entrySet();
            for (Map.Entry<String, JsonElement> entry : set) {
                String key = entry.getKey();
                if (key.equals(name)) {
                    json = getGson().toJson(entry.getValue());
                }
            }
            return getGson().fromJson(json, listType);
        } catch (Exception e) {
            return (T) new ArrayList<T>();
        }
    }

    private Gson getGson() {
        return new Gson();
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public JsonElement getResultData() {
        return resultData;
    }
}
