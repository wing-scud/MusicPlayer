package com.example.projectprepare1.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song
import com.example.android.roomwordssample.SongList

class ScanRepository (private val musicDao: MusicDao){
    fun deleteAll(){
        Thread(Runnable {
            musicDao.deleteAll()
        }).start()
    }
     fun insertSong(list: List<Song>) {
         Log.e("1111","-------"+list.size+"---------")
        for(index in 0..list.size-1){
            list[index].id=(index+1).toString()
            Thread(Runnable {
                musicDao.insertSong(list[index])
            }).start()
            Log.e("1111","-------"+list[index]+"---------")
        }
    }fun insertSongList(SongList:SongList){
        Log.e("1111","-------"+SongList+"---------")

        Thread(Runnable {
            musicDao.insertSongList(SongList)
        }).start()
    }
}