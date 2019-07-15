package com.example.projectprepare1.ui.scan

import android.app.Application
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song
import kotlinx.coroutines.launch

class ScanViewModel (application: Application) : AndroidViewModel(application) {
    var repository: ScanRepository
    object MusicUtils {
        var list = ArrayList<Song>()
        var visiable = true
        fun getMusicData(context: Context){
            // 媒体库查询语句（写一个工具类MusicUtils）
            list= ArrayList()
            visiable = true
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
                    song.id="1"
                    //歌曲名
                    song.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))
                    //歌手
                    song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                    //歌的路径
                    song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                    //歌的时间
                    song.duration = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                    //歌的大小
                    song.size = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))
                    var size1=song.size.toInt()
                    if (size1 > 1000 * 800) {
                        // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                        if (song.song!!.contains("-")) {
                            val str = song.song!!.split("-")
                            song.singer = str[0]
                            song.song = str[1]
                        }
                        var str=song.song!!.split(".")
                        if(str.size>1&&str[1].equals("mp3")){
                            song.song=str[0].trim()
                            list.add(song)
                        }
                    }
                }
                // 释放资源
                cursor.close()
                visiable = false
            }
        }
        fun getList():List<Song>{
            return list
        }
    }
    init {
       // val musicDao = MusicRoomDatabase.getDatabase(application, viewModelScope).musicDao()
        val musicDao = MusicRoomDatabase.instance.musicDao()
        repository = ScanRepository(musicDao)
    }
     fun insertSong(list: List<Song>) = viewModelScope.launch {
        repository.insertSong(list)
    }
    fun getAllSongs():List<Song>{
       return repository.getAllSongs()
    }
    fun insertSongList(){
        repository.insertSongList()
    }


}