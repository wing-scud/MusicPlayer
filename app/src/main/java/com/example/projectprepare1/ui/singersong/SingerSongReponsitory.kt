package com.example.projectprepare1.ui.singersong

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song

class SingerSongReponsitory (private val musicDao: MusicDao){
    var musicList: String? = null
    var currentMusic: Int? = 0
    fun getSingerSong(singerName: String): List<Song>{
        return musicDao.getSingerSongs(singerName)
    }
}