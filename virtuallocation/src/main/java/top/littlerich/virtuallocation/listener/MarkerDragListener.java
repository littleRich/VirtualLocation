package top.littlerich.virtuallocation.listener;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;

import static top.littlerich.virtuallocation.activity.MainActivity.setCurrentMapLatLng;

/**
 * Created by xuqingfu on 2017/5/4.
 */

public class MarkerDragListener implements BaiduMap.OnMarkerDragListener {
    /**
     * 地图上标记拖动
     * @param marker
     */
    @Override
    public void onMarkerDrag(Marker marker) {

    }

    /**
     * 地图上标记拖动结束
     * @param marker
     */
    @Override
    public void onMarkerDragEnd(Marker marker) {
        setCurrentMapLatLng(marker.getPosition());
    }

    /**
     * 地图上标记拖动开始
     * @param marker
     */
    @Override
    public void onMarkerDragStart(Marker marker) {

    }
}
