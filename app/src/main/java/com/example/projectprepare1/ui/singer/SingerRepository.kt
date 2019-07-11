package com.example.projectprepare1.ui.singer

import androidx.lifecycle.MutableLiveData
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song

class SingerRepository(private val musicDao: MusicDao) {
    var musicList: MutableLiveData<List<Song>>? =null
    var currentMusic: Int?=0 //当前播放歌曲
    fun getSingers(): Array<String>{
        return musicDao.getSingers()
    }
}