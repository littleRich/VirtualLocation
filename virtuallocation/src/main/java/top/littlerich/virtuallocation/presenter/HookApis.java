package top.littlerich.virtuallocation.presenter;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import top.littlerich.virtuallocation.common.AppApplication;

/**
 * Created by xuqingfu on 2017/5/26.
 */

public class HookApis {

    private static final  String TAG = "silence";

    /**
     * 修改高德地图的经纬度值
     * @param
     * @throws ClassNotFoundException
     */
    public static void findMethodAmapLongitudeAndLatitude(ClassLoader classLoader) {
        try {
            Class aMapLocationClazz = classLoader.loadClass("com.amap.api.location.AMapLocation");
            XposedHelpers.findAndHookMethod(aMapLocationClazz, "getLongitude", new Object[]{new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam arg3) throws Throwable {
                    arg3.setResult(Double.valueOf(AppApplication.mMockGps.mLongitude));
                    Log.d(TAG, "修改高德地图的经纬度值:" + AppApplication.mMockGps.mLongitude + ":" + AppApplication.mMockGps.mLatitude);
                }
            }});

            XposedHelpers.findAndHookMethod(aMapLocationClazz, "getLatitude", new Object[]{new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam arg3) throws Throwable {
                    arg3.setResult(Double.valueOf(AppApplication.mMockGps.mLatitude));
                }
            }});
        } catch (Exception e) {
            Log.i(TAG, "该程序无高德地图模块,无法HOOK");
        }
    }


}
