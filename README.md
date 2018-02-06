

<h1>VirtualLocation(UI仿共享单车OFO)</h1>
<p><a href="http://www.littlerich.top/">博客主页</a></p>
<p>对Android程序进行虚拟定位，可修改微信、QQ、陌陌以及一些打卡APP等软件，随意切换手机所处位置！（喜欢的给一个star, 有帮助的给一个fork， 欢迎Star和Fork ^_^）</p>
<p><a href="https://github.com/littleRich/VirtualLocation/blob/master/virtuallocation-release.apk">下载</a> 话不多说，试玩应用先。</p>

<h2>效果预览</h2>
<h3>主页</h3>
<div>
   <a href="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/app_index.gif" target="_blank">
      <img src="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/app_index.gif" alt="Alt text" style="max-width:100%;">
   </a>
</div>











----------


<h3>微信虚拟定位演示</h3>

1、打开本程序，选择好要穿越的地点，确认穿越即可！<br/>
<a href="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/location_xianggang.png" target="_blank"><img src="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/location_xianggang.png?raw=true" alt="Alt text" style="max-width:100%;"></a><a href="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/location_xinjiang.png?raw=true" target="_blank"><img src="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/location_xinjiang.png?raw=true" alt="Alt text" style="max-width:100%;"></a><br/>

2、再打开微信，这里演示在朋友圈发位置状态，如下：<br/>
<a href="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/location_result.png?raw=true" target="_blank"><img src="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/location_result.png?raw=true" alt="Alt text" style="max-width:100%;"></a>

----------

<h3>钉钉虚拟定位打卡演示</h3>


























----------


<h2>原理</h2>
<p>本程序有两种方式可以实现虚拟定位：</p>
<ol>
<li>通过ADB模拟定位功能</li>
<li>通过Hook修改获取经纬度API的值 （必需安装Xposed以及ROOT）</li>
</ol>

程序代码设计流程图如下：<br/>
<img href="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/code_design.png?raw=true" target="_blank"><img src="https://github.com/littleRich/VirtualLocation/blob/master/ShotScreen/code_design.png?raw=true" alt="Alt text" style="max-width:100%;"></img>

<p>第一种方式主要是是通过ADB模拟定位功能，再开启线程，不断的更新LocationManager的经纬度值，即可是实现定位模拟定位</p>

```java
	mMockThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(500);
                            if (!hasAddTestProvider) {
                                Log.d("xqf", "针对Android6.0+系统，需要单独把程序调加到ADB模拟定位服务中");
                                continue;
                            }
                            setLocation(LocationUtil.mLatitude, LocationUtil.mLongitude);
                            Log.d("xqf", "setLocation240=latitude:" + mLatitude + "?longitude:" + mLongitude);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                 }
                }
            });
            mMockThread.start();
```

<p>第二种方式主要是采用Hook修改系统API。目前很多程序都调用了isFromMockProvider方法来检测用户是否打开了ADB模拟定位功能，所以我又采用了Hook方式，就不怕不能虚拟定位了，具体如何Hook，可以看我的一篇博客：<a href="http://littlerich.top/2017/01/17/%E5%9F%BA%E4%BA%8EXposed%E6%A1%86%E6%9E%B6Hook%E5%AE%9A%E4%BD%8D%E5%8A%9F%E8%83%BD%E6%9D%A5%E7%A0%B4%E8%A7%A3QQ%E7%9A%84LBS%E7%BA%A2%E5%8C%85/">基于Xposed框架Hook定位功能来破解QQ的LBS红包</a></p>

----------

<h2>测试</h2>
<p>在Android测试机 分别是 魅蓝2、华为、SCL-TL00、Vivo xs1、红米note运行正常</p>

<h2>版本</h2>
<h3>v1.0</h3>
<p>完成了通过ADB模拟定位功能来虚拟定位，但是新版的一些程序都做了ADB模拟定位检测，所以现在很多新版本程序都不行了</p>
<h3>v1.1</h3>
<p>完善了通过Hook修改虚拟定位API，提高程序的兼容性和可行性，但同时也必须Android设备要ROOT</p>

<h2>issue</h2>
<p>如果程序运行有什么问题，可以先查看Issues中的问题回答，这样我就不用重复回答大家的问题了</p>
<p>Email:<a href="mailto:bugbye@163.com">bugbye@163.com</a></p>
<h2><a id="user-content-license" class="anchor" href="#license" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>License</h2>
<pre><code>Copyright 2016 xuqingfu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</code></pre>
</article>
  </div>

</div>






