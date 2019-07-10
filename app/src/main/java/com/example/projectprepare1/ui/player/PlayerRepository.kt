package com.example.projectprepare1.ui.player

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.MusicRepository
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song
import kotlinx.coroutines.launch


/**
2 * Copyright (C), 2015-2019, 雨女无瓜小组
3 * FileName: PlayerRepository
4 * Author: 小学艺
5 * Date: 2019/7/5 10:58
6 * Description: repo
7 * Notes：
9 * Time：2019年07月05日10时
9*/
 class PlayerRepository (private val dao:MusicDao){
    var musicList: MutableLiveData<List<Song>>? =null//播放歌单
    var currentMusic: Int?=0 //当前播放歌曲
    init {
       musicList =MutableLiveData(dao.getAllSongs())
    }
}
