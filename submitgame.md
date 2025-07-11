
# 提交游戏  (ps:提交的应用targetSdk必须>=35)

需要准备以下材料去提交包：

 ## 图片材料
 
 - 游戏图标，分辨率：512*512，必须是PNG或JPEG格式

 - 市场宣传图(最少2张,最多8张,一般是5张)，必须是PNG或JPEG格式，每张大小最多8 MB，16.9或9:16的纵横比，每边320像素到3840像素之间

 - 横图(1张)，分辨率：1024*500，必须是PNG或JPEG格式

 ## 文本材料
 
需要提供一个README.txt或者README.md 文本文件，里面的内容为：应用名称，包名，简介，描述，签名密码，别名，别名密码，隐私政策地址，游戏对应官网地址。

PS：应用名称需要特别注意，不能和Google play 上面的名称有相似的。可以参考[Google 假冒违规示例](https://support.google.com/googleplay/android-developer/answer/9888374/#zippy=%2C%E5%B8%B8%E8%A7%81%E8%BF%9D%E8%A7%84%E8%A1%8C%E4%B8%BA%E7%A4%BA%E4%BE%8B)

PS：应用名称需要特别注意，不能和Google play 上面的名称有相似的。可以参考[Google 假冒违规示例](https://support.google.com/googleplay/android-developer/answer/9888374/#zippy=%2C%E5%B8%B8%E8%A7%81%E8%BF%9D%E8%A7%84%E8%A1%8C%E4%B8%BA%E7%A4%BA%E4%BE%8B)

PS：应用名称需要特别注意，不能和Google play 上面的名称有相似的。可以参考[Google 假冒违规示例](https://support.google.com/googleplay/android-developer/answer/9888374/#zippy=%2C%E5%B8%B8%E8%A7%81%E8%BF%9D%E8%A7%84%E8%A1%8C%E4%B8%BA%E7%A4%BA%E4%BE%8B)

隐私政策参考：[免费隐私政策生成网站](https://www.freeprivacypolicy.com)

文本格式如下：

```ruby
1.应用名称： 30 字符以内 (应用名称不能包含Top，Best等排名相关的关键词)

2.包名: com.xxx.xxx.xx (包名不能以：com.example 开头)

3.简介： 80 字符以内 (简介不能包含Top，Best等排名相关的关键词)

4.描述：4000 字符以内

5.签名密码: xxxx

6.别名: xxx

7.别名密码:xxxx

8.隐私政策地址: https://www.freeprivacypolicy.com/live/8e92a503-576e-4f87-a17a-0da55cf42c81

9.游戏官网地址：https://xxxx.com（用于在域名根目录存放广告app-ads.txt文件）

```

 ## 其它材料

  - 打包用到的jks签名文件

  - 构建检测敏感权限的.apk包 

  - 构建正式的.aab包


**最终**

以上材料都准备好之后创建文件夹，把所有材料都放在里面，以应用名称命名，然后压缩成.zip文件发送给运营人员去提交审核包
