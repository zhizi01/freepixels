# FreePixels - Mods
FreePixels服务器插件开源项目

# 开发者(排名不算先后)
- 青山智子 (QQ:1635582152)
- 愿与君长安 (QQ:906506482)

# 代码贡献者
- 暂时没有

# 问题报告者
- 暂时没有

# 鸣谢名单
- 暂时没有

# 参考名单
- Fabric For Minecraft (https://fabricmc.net)

# 开源协议
开源遵循 Apache License 协议,任何人均可以修改并使用到自己的服务器中,若要二次分发,请标注原作者!

# 版权所有
Copyright (c) 2024 FreePixels All rights reserved.

# 构建自己的分发版本教学
-环境需求:
1. IDEA
2. Java(JDK17)
3. IDEA Minecraft生成器
4. Gradle

-部署教学:
1. 将项目以ZIP方式或Git方式下载到您的PC。
2. 将项目使用IDEA打开，弹出信任模式选择信任。
3. 等待IDEA完成识别并且执行成功Gradle，执行时间会有些长，但会完全自动完成所有依赖的安装以及Minecraft的代码下载。如果发生错误则为自己的网络问题，请自行百度如何切换国内源或使用科学上网。
4. 将Microsoft Defender配置设置为自动。
5. 等待后台任务Gradle配置项目成功后重启设备（目的清理缓存）。
6. 打开../src/java/org/freepixels/FreePixels.java可以看到IDEA右上角会出现一个三角形(运行)和一个虫子形(Debug)以及默认的选择:"Minecraft Client"。如果没有出现请重启设备。
7. 点击右上角的"Minecraft Client ↓"会出现一个位置菜单，点击"编辑配置"进入IDEA的"运行/调试配置"如下图。
   ![image](https://github.com/zhizi01/freepixels/assets/88622750/7b872a80-c121-4d95-98b6-f54337166efe)
8. 然后点击"+"号,选择"Gradle"并且按照图片中进行设置。
   ![image](https://github.com/zhizi01/freepixels/assets/88622750/6cfabaca-c452-4e3a-bc3b-02f26990976c)
9. 至此部署完成。

-常见问题:
1. 构建成功后模组去了哪里？
   答: 在你的"../build/libs"目录中,选择末尾不带"sources"的！带sources的是供开发调试用的。
2. 为什么在IDEA里我的服务端运行不成功？
   答: 与正常开服一样,需要到"../run/elua.txt"中同意协议。
3. 为什么我的服务器运行不了此插件，并且根据错误信息解析为版本不匹配？
   答: 本插件仅能用于Fabric服务端Java17的环境中，并且你的服务器"mods"目录下的"fabric-api-xxxx.jar"版本必须高于或等于本插件的"fabric-api"版本，如果需要调整请在"gradle.properties"文件中进行调整。
4. 为什么Liunx服务端插件报错并且服务器崩溃？
   答: 权限错误,确保读写权限为777。

若有其他问题请提出！

By: 青山智子.
