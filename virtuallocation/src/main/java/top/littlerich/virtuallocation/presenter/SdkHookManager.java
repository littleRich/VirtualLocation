package top.littlerich.virtuallocation.presenter;

import android.content.ContentResolver;
import android.location.Location;
import android.provider.Settings;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static android.provider.Settings.Secure.LOCATION_MODE_SENSORS_ONLY;

/**
 * Created by xuqingfu on 2017/5/31.
 */

public class SdkHookManager {

    /**
     * 判断是否开启允许ADB模拟定位功能
     */
     public static void findMethodIsFromMockProvider(){
         XposedHelpers.findAndHookMethod(Location.class, "isFromMockProvider", new XC_MethodHook() {
             @Override
             protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                 super.afterHookedMethod(param);
                 param.setResult(false);
             }
         });
     }

    /**
     * 劫持判断是否使用虚拟定位
     * @param loadPackageParam
     */
     public static void findMethodGetInt(XC_LoadPackage.LoadPackageParam loadPackageParam){
         // boolean isOpen = Settings.Secure.getInt(getContentResolver(),Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0;
         XposedHelpers.findAndHookMethod("android.provider.Settings$Secure", loadPackageParam.classLoader, "getInt", ContentResolver.class, String.class, int.class, new XC_MethodHook() {

             /**
              * 设置返回值类型：
              * 1、gpsEnabled && networkEnabled：LOCATION_MODE_HIGH_ACCURACY
              * 2、gpsEnabled：LOCATION_MODE_SENSORS_ONLY
              * 3、networkEnabled：LOCATION_MODE_BATTERY_SAVING
              * 4、disable：LOCATION_MODE_OFF
              */
             @Override
             protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                 super.afterHookedMethod(param);
                 param.setResult(LOCATION_MODE_SENSORS_ONLY);
             }
         });

         XposedHelpers.findAndHookMethod(Settings.Secure.class, "getInt", ContentResolver.class, String.class, int.class, new XC_MethodReplacement() {
             @Override
             protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                 return LOCATION_MODE_SENSORS_ONLY;
             }
         });
     }



}
