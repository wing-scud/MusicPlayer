package com.example.projectprepare1.ui.fond

import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song

class FondListReponsitory (private val musicDao: MusicDao){
    fun getFondList(listId: String):List<Song>{
        return musicDao.getSongsForSonglist(listId)
    }
}