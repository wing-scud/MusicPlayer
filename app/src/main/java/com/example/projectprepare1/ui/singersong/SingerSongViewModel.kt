package com.example.projectprepare1.ui.singersong

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song

class SingerSongViewModel(application: Application): AndroidViewModel(application){
    var reponsitory: SingerSongReponsitory
    init {
        val musicDao = MusicRoomDatabase.instance.musicDao()
        reponsitory = SingerSongReponsitory(musicDao)
    }
    fun getSingerSong(singerName:String):List<Song>{
        return reponsitory.getSingerSong(singerName)
    }
}