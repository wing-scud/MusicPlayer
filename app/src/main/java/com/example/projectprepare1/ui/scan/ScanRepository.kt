package com.example.projectprepare1.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song
import com.example.projectprepare1.ui.music.MusicViewModel

class ScanRepository (private val musicDao: MusicDao){
    var songList: LiveData<List<Song>> = musicDao.getAllSongs()
     fun insert(list: List<Song>) {
        for(index in 0..list.size-1){
            Thread(Runnable {
                musicDao.insertlist(list[index])
            }).start()
            Log.e("1111","-------"+list[index]+"---------")
        }
    }
}