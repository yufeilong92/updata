package com.example.dell.updata;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.service.DownloadService;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity.class";
    private Button mBtnShowDialog;
    private Button mButton;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnShowDialog = (Button) findViewById(R.id.btn_showDialog);
        mButton = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton6 = (Button) findViewById(R.id.button6);

        mBtnShowDialog.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_showDialog:
                updata();
                break;
            case R.id.button:

                break;
            case R.id.button2:

                break;
            case R.id.button3:

                break;
            case R.id.button4:

                break;
            case R.id.button5:

                break;
            case R.id.button6:

                break;
        }
    }

    private void updata() {
        UpdateAppBean updateAppBean = new UpdateAppBean();

        //设置 apk 的下载地址
        updateAppBean.setApkFileUrl("https://qd.myapp.com/myapp/qqteam/AndroidQQi/qq_6.0.0.6500_android_r24934_GuanWang_537055160_release.apk");

        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {
            try {
                path = getExternalCacheDir().getAbsolutePath();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(path)) {
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            }
        } else {
            path = getCacheDir().getAbsolutePath();
        }

        //设置apk 的保存路径
        updateAppBean.setTargetPath(path);
        //实现网络接口，只实现下载就可以
        updateAppBean.setHttpManager(new UpdateAppHttpUtil());

        UpdateAppManager.download(this, updateAppBean, new DownloadService.DownloadCallback() {
            @Override
            public void onStart() {
//                HProgressDialogUtils.showHorizontalProgressDialog(MainActivity.this, "下载进度", false);
                Log.d(TAG, "onStart() called");
            }

            @Override
            public void onProgress(float progress, long totalSize) {
//                HProgressDialogUtils.setProgress(Math.round(progress * 100));
                Log.d(TAG, "onProgress() called with: progress = [" + progress + "], totalSize = [" + totalSize + "]");

            }

            @Override
            public void setMax(long totalSize) {
                Log.d(TAG, "setMax() called with: totalSize = [" + totalSize + "]");
            }

            @Override
            public boolean onFinish(File file) {
//                HProgressDialogUtils.cancel();
                Log.d(TAG, "onFinish() called with: file = [" + file.getAbsolutePath() + "]");
                return true;
            }

            @Override
            public void onError(String msg) {
//                HProgressDialogUtils.cancel();
                Log.e(TAG, "onError() called with: msg = [" + msg + "]");
            }

            @Override
            public boolean onInstallAppAndAppOnForeground(File file) {
                Log.d(TAG, "onInstallAppAndAppOnForeground() called with: file = [" + file + "]");
                return false;
            }
        });
    }
}
