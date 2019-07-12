package com.example.projectprepare1.ui.scan


import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.roomwordssample.Song

import com.example.projectprepare1.R
import com.example.projectprepare1.ui.music.MusicViewModel
import kotlinx.android.synthetic.main.fragment_scan.*

class ScanFragment : Fragment() {
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    tv2.text = "共扫描到${msg.arg1.toString()}首歌"
                }
                2->{
                    if(msg.arg2==2){
                        scangif.visibility=View.GONE
                    }
                }
            }
        }
    }

        private lateinit var scanViewModel: ScanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scan.setOnClickListener {
            scangif.visibility=View.VISIBLE
            Log.e("111","++++++++++++++++++++++++")
            scanViewModel = ViewModelProviders.of(this).get(ScanViewModel::class.java)
            context?.let { it1 -> ScanViewModel.MusicUtils.getMusicData(it1) }
            scanViewModel.insertSong(ScanViewModel.MusicUtils.getList())
            scanViewModel.insertSongList()

            completeScan.visibility=View.VISIBLE
            var temp=0
            var pause=true
            var count = 0
            Thread(Runnable {
                while(pause) {
                    var data = scanViewModel.getAllSongs()
                    if (temp == data.size) {
                        count++
                    } else {
                        count = 0
                    }
                    if (count <= 200) {
                        temp = data.size
                        var message = Message()
                        message.what = 1
                        message.arg1 = temp
                        handler.sendMessage(message)
                        Log.e("222", "${temp}")
                    }
                    else{
                        var message = Message()
                        message.what=2
                        message.arg2=2
                        pause=false
                    }
                }
            }).start()
            tv2.visibility=View.VISIBLE
        }
        completeScan.setOnClickListener {
            scanViewModel.insertSonglistSongJoin(ScanViewModel.MusicUtils.getList())
            findNavController().navigate(R.id.action_scanFragment_to_localMusicFragment)
        }
    }
}
