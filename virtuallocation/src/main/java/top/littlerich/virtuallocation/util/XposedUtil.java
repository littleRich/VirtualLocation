package top.littlerich.virtuallocation.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuqingfu on 2017/4/17.
 */

public class XposedUtil {

    private static List<String> pkgs = new ArrayList<>();

    static {
        pkgs.add("com.tencent.mm");
        pkgs.add("com.tencent.mobileqq");
        pkgs.add("com.alibaba.android.rimet");
    }

    public static void hookAndChange(ClassLoader classLoader){

    }

    public static boolean isNeedHook(String pkgName){
        return pkgs.contains(pkgName);
    }

}
