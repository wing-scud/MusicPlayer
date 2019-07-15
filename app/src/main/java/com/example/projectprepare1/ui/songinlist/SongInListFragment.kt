package com.example.projectprepare1.ui.songinlist


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectprepare1.ui.player.PlayerActivity
import com.example.projectprepare1.ui.localmusic.MyClickListener
import com.example.projectprepare1.R
import com.hfut.music.MusicAdapter
import kotlinx.android.synthetic.main.fragment_song_in_list.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SongInListFragment : Fragment() {
    private lateinit var songInListViewModel: SongInListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_in_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songInListViewModel = ViewModelProviders.of(this).get(SongInListViewModel::class.java)
        var sharedPreferences=context!!.getSharedPreferences("temp",0)
        var temp=sharedPreferences.getInt("key",0)
        when(temp){
            0->{
                beijing6.setImageResource(R.drawable.bg3)
            }
            1-> {
                beijing6.setImageResource(R.drawable.beijing)
            }
            2-> {
                beijing6.setImageResource(R.drawable.beijing2)
            }
        }
        val listId = arguments?.getString("listId")!!
        val songListName = arguments?.getString("songListName")
        val song = songInListViewModel.getSongInList(listId)
        val adapter = MusicAdapter(context!!, R.layout.music_item, song,
            object : MyClickListener {
                override fun onClick(position: Int) {
                    val intent = Intent(context, PlayerActivity::class.java)
                    intent.putExtra("songList", songListName)
                    intent.putExtra("singer", "0")
                    intent.putExtra("local", "0")
                    intent.putExtra("song", song[position].song)
                    startActivity(intent)
                }
            })
        song_in_list.adapter = adapter
        song_in_list.layoutManager = LinearLayoutManager(context)
        song_in_list.layoutManager = GridLayoutManager(context, 1)
    }

}
