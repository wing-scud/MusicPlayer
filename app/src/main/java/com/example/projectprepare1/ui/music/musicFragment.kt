package com.example.projectprepare1.ui.music


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.roomwordssample.MusicRepository
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.projectprepare1.PlayerActivity
import com.example.projectprepare1.ui.localmusic.MyClickListener
import com.example.projectprepare1.R
import com.hfut.music.MusicAdapter
import kotlinx.android.synthetic.main.fragment_music.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MusicFragment : Fragment() {
    private lateinit var musicViewModel: MusicViewModel
    val musicDao = MusicRoomDatabase.instance.musicDao()
    val musicReponsitory = MusicRepository(musicDao)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        musicViewModel = ViewModelProviders.of(this).get(MusicViewModel::class.java)
        val data = musicViewModel.getSongs()
        val adapter = MusicAdapter(context!!, R.layout.music_item,data,
            object : MyClickListener {
                override fun onClick(position: Int) {
                    val intent = Intent(context, PlayerActivity::class.java)
                    intent.putExtra("local", "本地音乐")
                    intent.putExtra("songList","0")
                    intent.putExtra("singer","0")
                    intent.putExtra("song", data[position].song)
                    startActivity(intent)
                }
            })
        music_lv.adapter = adapter
        music_lv.layoutManager = LinearLayoutManager(context)
        music_lv.layoutManager = GridLayoutManager(context, 1)
    }

}
