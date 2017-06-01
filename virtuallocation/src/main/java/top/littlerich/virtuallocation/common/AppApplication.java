package top.littlerich.virtuallocation.common;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import top.littlerich.virtuallocation.model.Gps;

/**
 * Created by xuqingfu on 2017/4/15.
 */
public class AppApplication extends Application {

	/**全局GPS*/
	public static Gps mMockGps;

	static {
		mMockGps = Config.COMPANY;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
	}

}