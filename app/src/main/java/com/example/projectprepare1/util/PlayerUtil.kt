package com.example.projectprepare1.util

import android.util.Log
import com.example.android.roomwordssample.Song
import kotlin.random.Random

/**
2 * Copyright (C), 2015-2019, 雨女无瓜小组
3 * FileName: PlayerUtil
4 * Author: 小学艺
5 * Date: 2019/7/11 15:37
6 * Description:
7 * Notes：
9 * Time：2019年07月11日15时
9*/
class PlayerUtil {
    companion object{
         fun timeParse(duration: Long): String {
            var time = ""
            var minute = duration / 60000
            var seconds = duration % 60000
            var second = Math.round(seconds.toDouble() / 1000)
            if (minute < 10) {
                time += "0"
            }
            time += minute.toString() + ":"
            if (second < 10) {
                time += "0"
            }
            time += second
            return time
        }
        fun setNexFromPlayWay(playWay:Int,currentMusic:Int,musicListFra:List<Song>):Int{
            var nextSongId=0
            when(playWay){
                //TODO:播放方式
                1->{
                    nextSongId=currentMusic
                    Log.d("music","${ nextSongId}"+"        11111")
                }
                2->
                {
                    if(nextSongId>musicListFra?.size!!.toInt()-1){
                        nextSongId=0
                    }
                    else{
                        nextSongId=currentMusic+1
                    }

                    Log.d("music","${ nextSongId}"+"        2222")
                }
                3->
                {
                    nextSongId= Random.nextInt(musicListFra?.size!!)
                    Log.d("music","${ nextSongId}"+"           3333")}

            }
            return nextSongId
        }
        fun setAudioNext(turnSong:Int,currentMusic: Int,musicListFra: List<Song>):Int{
            var nextSongId=0
            when{
                currentMusic-turnSong>musicListFra?.size!!.toInt()-1 ->
                    nextSongId=0
                currentMusic-turnSong<0 ->
                    nextSongId=musicListFra?.size!!.toInt()-1
                else ->
                    nextSongId=currentMusic-turnSong
            }
            return nextSongId
        }
    }
}