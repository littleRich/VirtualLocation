

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












<h3>微信虚拟定位演示</h3>


<a href="https://github.com/mayubao/KuaiChuan/blob/master/ScreenShot/w_3.jpg" target="_blank"><img src="https://github.com/mayubao/KuaiChuan/raw/master/ScreenShot/w_3.jpg" alt="Alt text" style="max-width:100%;"></a></p>


<h2>原理</h2>
<p>本程序有两种方式可以实现虚拟定位：</p>
<ol>
<li>Android应用端发送到Android应用端（必须安装应用）</li>
<li>通过Web浏览器来实现文件的传送 （不必安装应用）</li>
</ol>
<p>第一种方式主要是是通过设备间发送文件。 文件传输在文件发送端或者是文件接收端通过自定义协议的Socket通信来实现。由于文件接收方和文件发送方都要有文件的缩略图，这里采用了header + body的自定义协议, header部分包括了文件的信息（长度，大小，缩略图）， body部分就是文件。</p>
<p>第二种方式主要是在android应用端架设微型Http服务器来实现文件的传输。这里可以用ftp来实现，为什么不用ftp呢？因为没有缩略图，这是重点！</p>
<h2><a id="user-content-测试" class="anchor" href="#测试" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>测试</h2>
<p>（必须在真机下测试）
在Android测试机 分别是 魅蓝2 与  华为 SCL-TL00， Vivo xs1 运行正常</p>
<h2><a id="user-content-感谢" class="anchor" href="#感谢" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>感谢</h2>
<p>google: <a href="http://www.google.com">http://www.google.com</a></p>
<p>stackoverflow  <a href="http://stackoverflow.com/">http://stackoverflow.com/</a></p>
<h2><a id="user-content-版本" class="anchor" href="#版本" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>版本</h2>
<h3><a id="user-content-v10" class="anchor" href="#v10" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>v1.0</h3>
<p>完成了Android端到Android端的文件传输</p>
<h3><a id="user-content-v11" class="anchor" href="#v11" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>v1.1</h3>
<p>完成了网页传模块的功能</p>







<h2>issue</h2>
<p>QQ:1209889831</p>
<p>Email:<a href="mailto:bugbye@163.com">bugbye@163.com</a></p>
<h2><a id="user-content-license" class="anchor" href="#license" aria-hidden="true"><svg aria-hidden="true" class="octicon octicon-link" height="16" version="1.1" viewBox="0 0 16 16" width="16"><path fill-rule="evenodd" d="M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z"></path></svg></a>License</h2>
<pre><code>Copyright 2016 mayubao

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






