package com.example.projectprepare1

import android.app.Application
import android.util.Log
import kotlin.properties.Delegates

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
    companion object {
        var instance: ItemApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate(){
        super.onCreate()
        var sharedPreferences=getSharedPreferences("temp",0)
        sharedPreferences.edit().clear().commit()
        instance = this
    }
}
