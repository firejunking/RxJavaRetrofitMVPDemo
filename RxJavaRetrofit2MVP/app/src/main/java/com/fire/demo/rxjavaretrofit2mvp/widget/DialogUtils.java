package com.fire.demo.rxjavaretrofit2mvp.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fire.demo.rxjavaretrofit2mvp.R;

/**
 * Created by pc on 2016/9/26.
 */
public class DialogUtils {
    private static DialogUtils mDialogUtils = null;
    private static Dialog mPopDialog;

    public static DialogUtils getInstance() {
        if (null == mDialogUtils) {
            synchronized (DialogUtils.class) {
                if (mDialogUtils == null) {
                    mDialogUtils = new DialogUtils();
                }
            }
        }
        return mDialogUtils;
    }


    public void popRemindDialog(Context context, String tvRes) {
        mPopDialog = new Dialog(context, R.style.AlertDialog);
        View view = mPopDialog.getWindow().getLayoutInflater().inflate(R.layout.dialog_alert, null);
        mPopDialog.setContentView(view);
        ((TextView) view.findViewById(R.id.dialog_alert_tv_text)).setText(tvRes);
        mPopDialog.setCanceledOnTouchOutside(false);
        mPopDialog.show();
    }

    public void disMissRemind() {
        if (mPopDialog != null && mPopDialog.isShowing()) {
            mPopDialog.dismiss();
            mPopDialog = null;
        }
    }
}
