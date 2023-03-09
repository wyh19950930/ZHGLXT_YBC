package com.jymj.zhglxt.ldrkgl.home.activity

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.journeyapps.barcodescanner.CaptureManager
import com.jymj.zhglxt.R
import kotlinx.android.synthetic.main.activity_scan.*


class ScanActivity : AppCompatActivity() {

    private var captureManager: CaptureManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        // 如果没有闪光灯功能，就去掉相关按钮
        if (!hasFlash()) {
            switch_light.setVisibility(View.GONE);
        }

        captureManager = CaptureManager(this, dbv_custom);
        captureManager!!.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager!!.decode();
        switch_light.setOnClickListener {

            Log.d("CJT" , "switch_light  --  onClick");
            switch_light.switchState(true);
            if(switch_light.isIconEnabled()){
                dbv_custom.setTorchOn(); // 打开手电筒
            }else{
                dbv_custom.setTorchOff(); // 关闭手电筒
            }
        }
        dbv_custom.setOnClickListener {
            Log.d("CJT" , "dbv_custom  --  onClick");
        }
        iv_act_scan_back.setOnClickListener {
            finish()
        }

    }

    override fun onPause() {
        super.onPause()
        captureManager?.onPause()
    }

    override fun onResume() {
        super.onResume()
        captureManager?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        captureManager?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        captureManager?.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return dbv_custom.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
//        return super.onKeyDown(keyCode, event)
    }

    // 判断是否有闪光灯功能
    private fun hasFlash():Boolean {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
    }

}
