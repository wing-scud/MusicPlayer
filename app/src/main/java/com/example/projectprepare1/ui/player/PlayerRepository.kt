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
    var currentMusic=MutableLiveData(0) //当前播放歌曲
    init {
       musicList =MutableLiveData(dao.getAllSongs())
    }
    //设置播放歌单
   fun setMusicList(listName:String){
        if(listName.equals("9999")){
            musicList?.value=dao.getAllSongs()
        }
        else{
            var listId=dao.getListId(listName)
            musicList?.value=dao.getSongsForSonglist(listId)
        }
   }
   fun setMusicListSinger(listName:String){
       musicList?.value=dao.getSingerSongs(listName)
   }
   fun setCurrentMusic(name:String){
      for(i in 0 until  musicList?.value!!.size){
         if(name==musicList?.value!![i].song) {
             currentMusic?.value=i
         }
         }
   }
   fun getSongMenu():Array<String>{
       var listDouble=dao.getSongList()
      var list= Array(listDouble.size, {""})
      for(i in 0 until listDouble.size){
          list[i]=(listDouble[i].name!!)
      }
      return  list
   }
   fun addSongToList(current:Int,songListName:String){
      var songId=musicList?.value!!.get(current).id
      var  songListId=dao.getListId(songListName)
      dao.insertSonglistSongJoin(SonglistSongJoin(songListId,songId))
   }
}
