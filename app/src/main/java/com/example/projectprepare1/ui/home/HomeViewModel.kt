package com.example.projectprepare1.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Songlist

class HomeViewModel(application: Application):AndroidViewModel(application){
    val songList:MutableLiveData<List<Songlist>>
    var repository: HomeRepository
    init {
        val musicDao = MusicRoomDatabase.instance.musicDao()
        repository = HomeRepository(musicDao)
        songList = repository.songList!!
    }
    fun getSongList():List<Songlist>{
        return repository.getSongList()
    }
    fun insertSongList(songList: Songlist){
        repository.insertSongList(songList)
    }
    fun deleteSongList(id:String, position:Int){
        repository.deleteSongList(id,position)
    }
}