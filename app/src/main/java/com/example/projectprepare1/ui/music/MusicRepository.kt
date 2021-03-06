package com.example.android.roomwordssample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class MusicRepository(private val musicDao: MusicDao) {
    var musicList: String? = null

    var currentMusic: Int? = 0
    val allSongs: List<Song> = musicDao.getAllSongs()
    suspend fun insertSong(song: Song) {
        musicDao.insertSong(song)
    }

}
