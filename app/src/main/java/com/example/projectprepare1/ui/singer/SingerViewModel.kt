package com.example.projectprepare1.ui.singer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.roomwordssample.MusicRoomDatabase

class SingerViewModel (application: Application):AndroidViewModel(application){
    var repository: SingerRepository
    init {
        val musicDao = MusicRoomDatabase.instance.musicDao()
        repository = SingerRepository(musicDao)
    }
    fun getSingers():Array<String>{
        return repository.getSingers()
    }
}