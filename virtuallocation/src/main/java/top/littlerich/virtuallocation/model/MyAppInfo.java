package top.littlerich.virtuallocation.model;

import android.graphics.drawable.Drawable;
/**
 * Created by xuqingfu on 17/4/24.
 */
public class MyAppInfo {
    private Drawable image;
    private String appName;
    private String pkgName;

    public MyAppInfo(Drawable image, String appName, String pkgName) {
        this.image = image;
        this.appName = appName;
        this.pkgName = pkgName;
    }
    public MyAppInfo() {

    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
