package com.example.projectprepare1.ui.fond

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song

class FondListViewModel(application: Application): AndroidViewModel(application){
    var reponsitory: FondListReponsitory
    init {
        val musicDao = MusicRoomDatabase.instance.musicDao()
        reponsitory = FondListReponsitory(musicDao)
    }
    fun FondList(listId: String):List<Song>{
        return reponsitory.getFondList(listId)
    }
}