package com.example.projectprepare1.ui.singer


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.projectprepare1.ui.localmusic.MyClickListener
import com.example.projectprepare1.R
import kotlinx.android.synthetic.main.fragment_singer.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SingerFragment : Fragment() {
    private lateinit var singerViewModel: SingerViewModel
    val musicDao = MusicRoomDatabase.instance.musicDao()
    var repository = SingerRepository(musicDao)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = Bundle()
        //val intent = Intent()
        super.onViewCreated(view, savedInstanceState)
        singerViewModel = ViewModelProviders.of(this).get(SingerViewModel::class.java)
        val singers = singerViewModel.getSingers()
        val adapter = SingerAdapter(context!!, R.layout.singer_item, singers,
            object : MyClickListener {
                override fun onClick(position: Int) {
                    val bundle = Bundle()
                    bundle.putString("singerName",singers[position])
                    findNavController().navigate(R.id.action_localMusicFragment_to_singerSongFragment,bundle)
                }
            })
        singer_lv.adapter = adapter
        singer_lv.layoutManager = LinearLayoutManager(context)
        singer_lv.layoutManager = GridLayoutManager(context, 1)
    }

}
