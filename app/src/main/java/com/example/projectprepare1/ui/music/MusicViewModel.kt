package com.example.projectprepare1.ui.music

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomwordssample.MusicRepository
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song
import kotlinx.coroutines.launch

class MusicViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MusicRepository

    init {
        val musicDao = MusicRoomDatabase.instance.musicDao()
        repository = MusicRepository(musicDao)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */

    fun getSongs():List<Song>{
        return repository.allSongs
    }
}
