package com.example.projectprepare1.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song
import com.example.android.roomwordssample.Songlist
import com.example.android.roomwordssample.SonglistSongJoin

class ScanRepository (private val musicDao: MusicDao){
    var songs: MutableLiveData<List<Song>>? = null
    val data = mutableListOf<Song>()
     fun insertSong(list: List<Song>) {
         Thread(Runnable {
         for(index in 0..list.size-1){
            list[index].id=(index+1).toString()
             musicDao.insertSong(list[index])
         }
            }).start()
    }fun getAllSongs():List<Song>{
        return musicDao.getAllSongs()
    }

    init {
        songs = MutableLiveData(musicDao.getAllSongs())
        data.addAll(musicDao.getAllSongs())
    }
    fun insertSongList(){
        musicDao.insertSongList(Songlist("1","我的喜爱"))
    }

}