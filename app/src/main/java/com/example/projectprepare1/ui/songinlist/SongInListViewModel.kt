package com.example.projectprepare1.ui.songinlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song

class SongInListViewModel(application: Application): AndroidViewModel(application){
    var reponsitory: SongInListReponsitory
    init {
        val musicDao = MusicRoomDatabase.instance.musicDao()
        reponsitory = SongInListReponsitory(musicDao)
    }
    fun getSongInList(listId: String):List<Song>{
        return reponsitory.getSongInList(listId)
    }
}