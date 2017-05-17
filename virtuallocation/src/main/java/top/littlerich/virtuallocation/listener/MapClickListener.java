package top.littlerich.virtuallocation.listener;

import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.model.LatLng;

import static top.littlerich.virtuallocation.activity.MainActivity.setCurrentMapLatLng;

/**
 * 地图单击事件监听接口
 * Created by xuqingfu on 2017/5/4.
 */

public class MapClickListener implements BaiduMap.OnMapClickListener {

    private Button mLocOk;

    public MapClickListener(Button locOk) {
        mLocOk = locOk;
    }

    /**
     * 地图单击事件
     * @param latLng 点击的地理坐标
     */
    @Override
    public void onMapClick(LatLng latLng) {
        setCurrentMapLatLng(latLng);
        if ("穿越完成".equals(mLocOk.getText().toString())) {
            mLocOk.setText("修改位置");
        }
    }

    /**
     * 地图内 Poi 单击事件
     * @param mapPoi  点击的 poi 信息
     * @return
     */
    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
}
