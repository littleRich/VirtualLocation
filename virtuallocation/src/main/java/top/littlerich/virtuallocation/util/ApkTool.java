package top.littlerich.virtuallocation.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import top.littlerich.virtuallocation.model.MyAppInfo;

/**
 * 扫描本地安装的应用,工具类
 * <p>
 * Created by xuqingfu on 2017/4/24.
 */
public class ApkTool {
    static String TAG = "ApkTool";
    public static List<MyAppInfo> mLocalInstallApps = null;

    public static List<MyAppInfo> scanLocalInstallAppList(PackageManager packageManager, boolean isFilter) {
        List<MyAppInfo> myAppInfos = new ArrayList<MyAppInfo>();
        try {
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo packageInfo = packageInfos.get(i);
                //过滤掉系统app
                if (isFilter && (ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                    continue;
                }
                MyAppInfo myAppInfo = new MyAppInfo();
                myAppInfo.setPkgName(packageInfo.applicationInfo.loadLabel(packageManager).toString());

                myAppInfo.setAppName(packageInfo.packageName);
                if (packageInfo.applicationInfo.loadIcon(packageManager) == null) {
                    continue;
                }
                myAppInfo.setImage(packageInfo.applicationInfo.loadIcon(packageManager));
                myAppInfos.add(myAppInfo);
            }
        } catch (Exception e) {
            Log.e(TAG, "===============获取应用包信息失败");
        }
        return myAppInfos;
    }

}
