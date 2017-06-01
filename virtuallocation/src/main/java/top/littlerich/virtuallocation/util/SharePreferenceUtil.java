package top.littlerich.virtuallocation.util;

import android.content.Context;
import android.content.SharedPreferences;

import top.littlerich.virtuallocation.common.Common;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by xuqingfu on 2017/3/14.
 */

public class SharePreferenceUtil {

    private static final String SP_FILENAME = "config";

    public static void saveData(Context context, String userName, String psd) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILENAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Common.SP_FILTER_APP_PKG_NAME, userName);
        editor.commit();
    }

    public static boolean loadData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_FILENAME, MODE_PRIVATE);
   //     Common.USER_NAME = sp.getString(Common.SP_FILTER_APP_PKG_NAME, "").toString();
        /*if (TextUtils.isEmpty(Common.USER_NAME) || TextUtils.isEmpty(Common.USER_PWD)) {
            return false;
        }*/
        return true;
    }

    public static void cleanData(Context context){
        saveData(context, "", "");
    }

}
