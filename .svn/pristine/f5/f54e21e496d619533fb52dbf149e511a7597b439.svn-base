package com.jymj.zhglxt.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.setsuna.common.commonutils.ToastUtils;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/8/25 11:30
 */
public class PopuBcjcRejectUtils {
    private static CommenPop mPointPopu;
    private static Activity activity;
    private static EditText et_reject;
    private static TextView confirm;
    private static TextView close;
    public static void initPopuPoint(Activity activity1, View view, EditText editText) {
        activity = activity1;
        mPointPopu = CommenPop.getNormalPopu(activity, R.layout.pop_bcjc_reject, view);//pop_point
        View contentView = mPointPopu.getContentView();
        et_reject = contentView.findViewById(R.id.pop_bcjc_reject_et_reject);
        confirm = contentView.findViewById(R.id.pop_bcjc_reject_confirm);
        close = contentView.findViewById(R.id.pop_bcjc_reject_close);
        CommenPop.backgroundAlpha(0.5f, activity);
        mPointPopu.showAtLocation(view, Gravity.CENTER, 0, 0);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_reject.getText().toString().equals("")){
                    ToastUtils.showLong("请输入驳回内容");
                }else {
                    editText.setText(et_reject.getText().toString());
                    mPointPopu.dismiss();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointPopu.dismiss();
            }
        });
    }
}
