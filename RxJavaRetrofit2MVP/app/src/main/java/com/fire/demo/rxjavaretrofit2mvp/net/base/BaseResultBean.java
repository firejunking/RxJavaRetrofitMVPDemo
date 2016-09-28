package com.fire.demo.rxjavaretrofit2mvp.net.base;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class BaseResultBean {
    private String actionType;
    @SerializedName("Response")
    private JsonElement response;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public JsonElement getResponse() {
        return response;
    }

    public void setResponse(JsonElement response) {
        this.response = response;
    }

}
