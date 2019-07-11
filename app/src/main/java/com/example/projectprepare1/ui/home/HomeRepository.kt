package com.example.projectprepare1.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Songlist

class HomeRepository (private val musicDao: MusicDao){
    var songList: MutableLiveData<List<Songlist>>? = null
    val data = mutableListOf<Songlist>()
    fun getSongList():List<Songlist>{
        return musicDao.getSongList()
    }
    fun insertSongList(songList:Songlist){
        musicDao.insertSongList(songList)
        data.add(songList)
        this.songList?.postValue(data)
    }
    fun deleteSongList(id:String, position:Int){
        musicDao.deleteSongList(id)
        data.removeAt(position)
        this.songList?.postValue(data)
    }
    init {
        songList = MutableLiveData(musicDao.getSongList())
        data.addAll(musicDao.getSongList())
    }
}