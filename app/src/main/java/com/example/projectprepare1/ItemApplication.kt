package com.example.projectprepare1

import android.app.Application

/**
2 * Copyright (C), 2015-2019, XXX有限公司
3 * FileName: ItemApplication
4 * Author: 小学艺
5 * Date: 2019/7/4 17:10
6 * Description: 全局类，初始化app
7 * History:
8 * <author> <time> <version> <desc>
9 * 作者姓名 修改时间 版本号 描述
10 */
class ItemApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        //全局初始化，如扫描本地.mp3文件
    }
}