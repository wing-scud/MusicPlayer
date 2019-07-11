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

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.Song
import com.example.android.roomwordssample.Songlist
import com.example.projectprepare1.ItemApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */

@Database(
    entities = [Song::class, Songlist::class, SonglistSongJoin::class],
    version = 1)
abstract class MusicRoomDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao
        //以单例模式创建数据库
        companion object{
            val instance = Singleton.single
        }

        private object Singleton{
            val single:MusicRoomDatabase = Room.databaseBuilder(
                ItemApplication.instance,
                MusicRoomDatabase::class.java,
                "PlanA.db"
            )
                .allowMainThreadQueries()
                .build()

        }
    }
