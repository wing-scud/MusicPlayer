package com.example.android.roomwordssample

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

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

/**
 * The Room Magic is in this file, where you map a Java method call to an SQL query.
 *
 * When you are using complex data types, such as Date, you have to also supply type converters.
 * To keep this example basic, no types that require type converters are used.
 * See the documentation at
 * https://developer.android.com/topic/libraries/architecture/room.html#type-converters
 */

@Dao
interface MusicDao {
    @Query("SELECT * FROM song_table")
    fun getAllSongs(): List<Song>
    @Query("SELECT * FROM song_table WHERE song_table.id LIKE :songId")
    fun getSong(songId: String): Song
    @Query("SELECT DISTINCT singer FROM song_table")
    fun getSingers(): Array<String>
    @Query("SELECT * FROM song_table WHERE singer = :singerName")
    fun getSingerSongs(singerName: String): List<Song>
    @Query("SELECT * FROM songlist_table")
    fun getSongList(): List<Songlist>

    @Query("SELECT  id FROM songlist_table WHERE name = :listName")
    fun getListId(listName: String):String
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSong(song: Song)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSongList(songlist: Songlist)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSonglistSongJoin(songlistSongJoin: SonglistSongJoin)

    @Update
    fun updateSong(song: Song)
    @Update
    fun updateSonglist(songlist: Songlist)

    @Query("DELETE FROM song_table")
    fun deleteAll()
    @Query("DELETE FROM songlist_table WHERE id = :id")
    fun deleteSongList(id: String)


    @Query("""
           SELECT * FROM songlist_table
           INNER JOIN songlist_song_join_table
           ON songlist_table.id LIKE songlist_song_join_table.songlistId
           WHERE songlist_song_join_table.songId LIKE :songId
           """)
    fun getSonglistsForSong(songId: String): Array<Songlist>
    @Query("""
           SELECT * FROM song_table
           INNER JOIN songlist_song_join_table
           ON song_table.id LIKE songlist_song_join_table.songId
           WHERE songlist_song_join_table.songlistId LIKE :songlistId
           """)
    fun getSongsForSonglist(songlistId: String): List<Song>
}
