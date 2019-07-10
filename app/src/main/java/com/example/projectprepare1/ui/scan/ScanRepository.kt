package com.example.projectprepare1.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song

class ScanRepository (private val musicDao: MusicDao){
    fun deleteAll(){
        Thread(Runnable {
            musicDao.deleteAll()
        }).start()
    }
     fun insertSong(list: List<Song>) {
         Log.e("1111","-------"+list.size+"---------")
         Thread(Runnable {
         for(index in 0..list.size-1){
            list[index].id=(index+1).toString()
             musicDao.insertSong(list[index])
             Log.e("1111","-------"+list[index]+"---------")
         }
            }).start()


    }
}