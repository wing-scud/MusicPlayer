package com.example.projectprepare1.ui.singer


import com.example.android.roomwordssample.MusicDao


class SingerRepository(private val musicDao: MusicDao) {
    fun getSingers(): Array<String>{
        return musicDao.getSingers()
    }
}