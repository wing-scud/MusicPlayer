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
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allSongs: LiveData<List<Song>>

    init {
        val musicDao = MusicRoomDatabase.getDatabase(application, viewModelScope).musicDao()
        repository = MusicRepository(musicDao)
        allSongs = repository.allSongs
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(song: Song) = viewModelScope.launch {
        repository.insert(song)
    }
}
