package com.example.projectprepare1.ui.singersong


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.projectprepare1.PlayerActivity
import com.example.projectprepare1.ui.localmusic.MyClickListener
import com.example.projectprepare1.R
import com.hfut.music.MusicAdapter
import kotlinx.android.synthetic.main.fragment_singer_song.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SingerSongFragment : Fragment() {
    private lateinit var singerSongViewModel: SingerSongViewModel
    val musicDao = MusicRoomDatabase.instance.musicDao()
    var reponsitory = SingerSongReponsitory(musicDao)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singer_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val singer = arguments?.getString("singerName")!!

        singerSongViewModel = ViewModelProviders.of(this).get(SingerSongViewModel::class.java)
        val singerSong = singerSongViewModel.getSingerSong(singer)
        Log.i("============",singerSong.toString())
        val adapter = MusicAdapter(context!!, R.layout.music_item, singerSong,
            object : MyClickListener {
                override fun onClick(position: Int) {
                    val intent = Intent(context, PlayerActivity::class.java)
                    intent.putExtra("singer",singer)
                    intent.putExtra("local", "0")
                    intent.putExtra("songList","0")
                    intent.putExtra("song",singerSong[position].song)
                    startActivity(intent)
                }
            })
        singer_song_lv.adapter = adapter
        singer_song_lv.layoutManager = LinearLayoutManager(context)
        singer_song_lv.layoutManager = GridLayoutManager(context, 1)
    }


}
