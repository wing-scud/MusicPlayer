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
         Log.e("1111","-------"+list.size+"---------")
         Thread(Runnable {
         for(index in 0..list.size-1){
            list[index].id=(index+1).toString()
             musicDao.insertSong(list[index])
             Log.e("1111","-------"+list[index]+"---------")
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
        musicDao.insertSongList(Songlist("9999",("本地音乐")))
        musicDao.insertSongList(Songlist("10000",("我的喜爱")))
    }
    fun insertSonglistSongJoin(list: List<Song>) {
        for (index in 0..list.size - 1) {
            list[index].id = (index + 1).toString()
            var songlistSongJoin = SonglistSongJoin("9999", list[index].id)
            musicDao.insertSonglistSongJoin(songlistSongJoin)
        }
    }
}