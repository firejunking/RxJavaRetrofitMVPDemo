package com.fire.demo.rxjavaretrofit2mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import com.fire.demo.rxjavaretrofit2mvp.R;
import com.fire.demo.rxjavaretrofit2mvp.bean.UserInfo;
import com.fire.demo.rxjavaretrofit2mvp.mvp.base.MvpActivity;
import com.fire.demo.rxjavaretrofit2mvp.mvp.persenter.UserLoginPresenter;
import com.fire.demo.rxjavaretrofit2mvp.mvp.view.IUserLoginView;
import com.fire.demo.rxjavaretrofit2mvp.widget.DialogUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pc on 2016/9/26.
 */
public class LoginActivity extends MvpActivity<UserLoginPresenter> implements IUserLoginView {
    private Context mContext;
    @Bind(R.id.editText_name)
    EditText mEtGwNumber;
    @Bind(R.id.editText_pwd)
    EditText mEtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
    }

    @Override
    protected UserLoginPresenter createPresenter() {
        return new UserLoginPresenter(this);
    }

    @OnClick(R.id.button_login)
    public void onClickLogin() {
        Map<String, String> maps = new HashMap<>();
        maps.put("name", mEtGwNumber.getText().toString());
        maps.put("pwd", mEtPassword.getText().toString());
        mvpPresenter.login(maps);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFail(int toast, String reason) {
        if (reason.isEmpty()) {
            Toast.makeText(LoginActivity.this, getString(toast), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, reason, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showLoading() {
        DialogUtils.getInstance().popRemindDialog(mContext, "正在登录");
    }

    @Override
    public void hideLoading() {
        DialogUtils.getInstance().disMissRemind();
    }

    @Override
    public void saveCommonUserInfo(UserInfo info) {
        //这里将登录接口返回的数据，根据实际情况存储在SP或者SQLite
    }
}
