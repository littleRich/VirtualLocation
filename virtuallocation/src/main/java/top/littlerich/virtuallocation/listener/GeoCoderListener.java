package top.littlerich.virtuallocation.listener;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/**
 * 地理编码:1、正向地图编码 2、反向地图编码
 * Created by xuqingfu on 2017/5/4.
 */

public class GeoCoderListener implements OnGetGeoCoderResultListener {

    private Context mContext;
    private TextView mLocationTip;

    public GeoCoderListener(Context context, TextView locationTip) {
        mContext = context;
        mLocationTip = locationTip;
    }

    /**
     * 搜索（根据实地信息-->经纬坐标）
     * @param geoCodeResult
     */
    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    /**
     * 搜索（根据坐标-->实地信息）
     * @param result
     */
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(mContext, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
            return;
        }
        mLocationTip.setText(String.format("伪造位置：%s", result.getAddress()));
    }
}
