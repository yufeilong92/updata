package com.example.dell.updata;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: app.java
 * @Package com.example.dell.updata
 * @Description: todo
 * @author: YFL
 * @date: 2018/5/28 22:07
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/5/28 星期一
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpUtils.getInstance()
                .init(this)
                .debug(true, "okHttp")
                .timeout(20 * 1000);


        OkGo.getInstance().init(this);
    }
}
