package com.example.projectprepare1.ui.fond


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
import com.example.projectprepare1.PlayerActivity

import com.example.projectprepare1.R
import com.example.projectprepare1.ui.localmusic.MyClickListener
import com.example.projectprepare1.ui.songinlist.SongInListViewModel
import com.hfut.music.MusicAdapter
import kotlinx.android.synthetic.main.fragment_fond_list.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_song_in_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FondListFragment : Fragment() {
    private lateinit var fondListViewModel: FondListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fond_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedPreferences=context!!.getSharedPreferences("temp",0)
        var temp=sharedPreferences.getInt("key",0)
        when(temp){
            0->{
                imageView2.setImageResource(R.drawable.bg3)
            }
            1-> {
                imageView2.setImageResource(R.drawable.beijing)
            }
            2-> {
                imageView2.setImageResource(R.drawable.beijing2)
            }
        }
        fondListViewModel = ViewModelProviders.of(this).get(FondListViewModel::class.java)
        val data = fondListViewModel.FondList("1")
        val adapter = MusicAdapter(context!!, R.layout.music_item, data,
            object : MyClickListener {
                override fun onClick(position: Int) {
                    val intent = Intent(context, PlayerActivity::class.java)
                    intent.putExtra("local","0")
                    intent.putExtra("songList","我的喜爱")
                    intent.putExtra("singer","0")
                    intent.putExtra("song", data[position].song)
                    startActivity(intent)
                }
            })
        fond_list.adapter = adapter
        fond_list.layoutManager = LinearLayoutManager(context)
        fond_list.layoutManager = GridLayoutManager(context, 1)
    }


}
