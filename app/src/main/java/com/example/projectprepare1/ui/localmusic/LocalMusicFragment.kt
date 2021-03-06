package com.example.projectprepare1.ui.localmusic


import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.projectprepare1.R
import com.example.projectprepare1.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_local_music.*
import kotlinx.android.synthetic.main.fragment_music.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LocalMusicFragment : Fragment() {
    private lateinit var homeViewModel:HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedPreferences=context!!.getSharedPreferences("temp",0)
        var temp=sharedPreferences.getInt("key",0)
        when(temp){
            0->{
                beijing2.setImageResource(R.drawable.bg3)
            }
            1-> {
                beijing2.setImageResource(R.drawable.beijing)
            }
            2-> {
                beijing2.setImageResource(R.drawable.beijing2)
            }
        }
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val songList = homeViewModel.getSongList()
        pager.adapter = TabAdapter(childFragmentManager)
        tab.setupWithViewPager(pager)

    }



}
