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
      Log.d("next","${musicList?.value!!.size}"+"    repo  list")
        if(listName.equals("9999")){
            musicList?.value=dao.getAllSongs()
        }
        else{
            musicList?.value=dao.getSongsForSonglist(listName)
        }
   }
   fun setMusicListSinger(listName:String){
       musicList?.value=dao.getSingerSongs(listName)
       Log.d("next","${musicList?.value!!.size}"+"    repo  singer")
   }
   fun setCurrentMusic(name:String){
      for(i in 0 until  musicList?.value!!.size){
         if(name==musicList?.value!![i].song) {
             currentMusic?.value=i
             Log.d("temp ","${name}"+"  currrent  name  repo    ")
            Log.d("temp ","${currentMusic.value}"+"  currrent repo    ")
         }
         }
   }
   fun getSongMenu():Array<String>{
      var list= arrayOf(String())
      var listDouble=dao.getSongList()
       Log.d("save","${listDouble.size}"+" repo  listDouble size ")
      for(i in 0 until listDouble.size){
          Log.d("save","${listDouble[i].name!!}"+"  listDouble[i].name!! 1 ")
          list.set(i,listDouble[i].name!!)
          Log.d("save","${listDouble[i].name!!}"+"   listDouble[i].name!!  2 ")
      }
       Log.d("save","${list.size!!}"+"   listsize!!    ")
      return  list
   }
   fun addSongToList(current:Int,songListName:String){
      var songId=musicList?.value!!.get(current).id
       Log.d("save ","${songId}"+"       songId   repo ")
      var  songListId=dao.getListId(songListName)
       Log.d("save ","${songListId}"+"           songListId repo ")
      dao.insertSonglistSongJoin(SonglistSongJoin(songId,songListId))
   }
}
