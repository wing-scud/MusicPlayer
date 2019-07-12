package com.example.projectprepare1.ui.player

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomwordssample.*
import kotlinx.coroutines.launch


/**
2 * Copyright (C), 2015-2019, 雨女无瓜小组
3 * FileName: PlayerRepository
4 * Author: 小学艺
5 * Date: 2019/7/5 10:58
6 * Description: repo
7 * Notes：
9 * Time：2019年07月05日10时
9*/
 class PlayerRepository (private val dao:MusicDao){
    var musicList: MutableLiveData<List<Song>>? =null//播放歌单
    var currentMusic: Int?=0 //当前播放歌曲
    init {
       musicList =MutableLiveData(dao.getAllSongs())
    }
   fun setMusicList(listName:String){
      Log.d("next","${musicList?.value!!.size}"+"    repo  lsit")
      musicList= MutableLiveData(dao.getSongsForSonglist(listName))
   }
   fun setMusicListSinger(listName:String){
      musicList= MutableLiveData(dao.getSingerSongs(listName))
   }
   fun setCurrentMusic(name:String){
      for(i in 0 until  musicList?.value!!.size){
         if(name==musicList?.value!!.get(i).song) {
            Log.d("next","${currentMusic}"+"  currrent ")
            currentMusic=i
         }
         }
   }
   fun getSongMenu():Array<String>{
      var list= arrayOf(String())
      var listDouble=dao.getSongList()
      for(i in 0 until listDouble.size){
         list.set(i,listDouble[1].name!!)
      }
      return  list
   }
   fun addSongToList(current:Int,songListName:String){
      var songId=musicList?.value!!.get(current).id
      var  songListId=""
     // var  songListId=dao.getSongListId(songListName)
      dao.insertSonglistSongJoin(SonglistSongJoin(songId,songListId))
   }
}
