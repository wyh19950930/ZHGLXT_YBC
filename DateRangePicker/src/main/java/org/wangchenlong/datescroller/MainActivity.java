package org.wangchenlong.datescroller;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.wangchenlong.datescroller.widget.DateScrollerDialog;
import org.wangchenlong.datescroller.widget.data.Type;
import org.wangchenlong.datescroller.widget.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年

    private TextView mTvTime;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);//yyyy-MM-dd
    private long mLastTime = System.currentTimeMillis(); // 上次设置的开始时间
    private long mLastFinishTime = System.currentTimeMillis(); // 上次设置的结束时间

    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override public void onDateSet(DateScrollerDialog timePickerView, long milliseconds, long milliFinishseconds) {
            mLastTime = milliseconds;
            mLastFinishTime = milliFinishseconds;
            String text = getDateToString(milliseconds);
            String text2 = getDateToString(milliFinishseconds);
            mTvTime.setText(text + "-" + text2);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); // 初始化View
    }

    /**
     * 显示日期
     *
     * @param view 视图
     */
    public void showDate(View view) {
        // 选择日期
        DateScrollerDialog dialog = new DateScrollerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("请选择日期")
                .setMinMilliseconds(System.currentTimeMillis() - HUNDRED_YEARS)
                .setMaxMilliseconds(System.currentTimeMillis() + HUNDRED_YEARS)
                // 上次设置的开始时间，结束时间
                .setCurMilliseconds(mLastTime, mLastFinishTime)
                .setCallback(mOnDateSetListener)
                .build();

        if (dialog != null) {
            if (!dialog.isAdded()) {
//                dialog.show(getSupportFragmentManager(), "year_month_day");
            }
        }
    }

    // 初始化视图
    private void initView() {
        mTvTime = (TextView) findViewById(R.id.tv_time);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
