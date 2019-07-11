package com.example.projectprepare1.ui.songinlist

import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song

class SongInListReponsitory (private val musicDao: MusicDao){
    fun getSongInList(listId: String):List<Song>{
        return musicDao.getSongsForSonglist(listId)
    }
}