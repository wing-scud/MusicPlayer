package com.example.projectprepare1.ui.scan

import android.app.Application
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomwordssample.MusicRepository
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song
import kotlinx.coroutines.launch

class ScanViewModel (application: Application) : AndroidViewModel(application) {
    var repository: ScanRepository
    var songList: LiveData<List<Song>>
    object MusicUtils {
        var list = ArrayList<Song>()
        fun getMusicData(context: Context){
            // 媒体库查询语句（写一个工具类MusicUtils）
            val cursor = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.AudioColumns.IS_MUSIC
            )
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val song = Song("","","","","","")
//                    song.id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID))
                    song.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                    song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                    song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                    song.duration = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                    song.size = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))
                    var size1=song.size.toInt()
                    if (size1 > 1000 * 800) {
                        // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                        if (song.song!!.contains("-")) {
                            val str = song.song!!.split("-")
                            song.singer = str[0]
                            song.song = str[1]
                        }
                        list.add(song)
                    }
                }
                // 释放资源
                cursor.close()
            }
        }
        /**
         * 定义一个方法用来格式化获取到的时间
         */
        fun getList():List<Song>{
            return list
        }
    }
    init {
        val musicDao = MusicRoomDatabase.getDatabase(application, viewModelScope).musicDao()
        repository = ScanRepository(musicDao)
        songList = repository.songList
    }
     fun insert(list: List<Song>) = viewModelScope.launch {
        repository.insert(list)
    }
}