package com.example.projectprepare1.ui.singersong

import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song

class SingerSongReponsitory (private val musicDao: MusicDao){
    fun getSingerSong(singerName: String): List<Song>{
        return musicDao.getSingerSongs(singerName)
    }
}