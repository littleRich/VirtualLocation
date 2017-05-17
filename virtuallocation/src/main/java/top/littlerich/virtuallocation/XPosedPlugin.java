package top.littlerich.virtuallocation;

import android.content.ContentResolver;
import android.location.Location;
import android.provider.Settings;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import top.littlerich.virtuallocation.util.XposedUtil;

import static android.provider.Settings.Secure.LOCATION_MODE_SENSORS_ONLY;

/**
 * Created by xuqingfu on 2017/4/15.
 */

public class XPosedPlugin implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        /*XposedHelpers.findAndHookMethod(Location.class, "isFromMockProvider", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(false);
                Log.e("gps", "XposedHelpers01");
            }
        });

        XposedHelpers.findAndHookMethod("android.location.Location", loadPackageParam.classLoader, "isFromMockProvider", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(false);
                Log.e("gps", "XposedHelpers02");
            }
        });*/

        //只Hook微信定位请求
        if (XposedUtil.isNeedHook(loadPackageParam.packageName)) {
            XposedHelpers.findAndHookMethod(Location.class, "isFromMockProvider", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {

                    return false;
                }
            });

            //劫持判断是否使用虚拟定位
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

            XposedHelpers.findAndHookMethod(Location.class, "isFromMockProvider", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(false);
                }
            });


        }


    }


}
