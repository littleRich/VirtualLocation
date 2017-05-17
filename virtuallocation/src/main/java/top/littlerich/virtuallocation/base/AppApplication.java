package top.littlerich.virtuallocation.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by xuqingfu on 2017/4/15.
 */
public class AppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
	}

}