package com.example.projectprepare1.ui.home


import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.graphics.Typeface
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.MusicDao
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.android.roomwordssample.Song
import com.example.android.roomwordssample.Songlist
import com.example.projectprepare1.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.input_list_name.*
import kotlinx.android.synthetic.main.music_item.*
import kotlinx.android.synthetic.main.singer_item.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_local_music_ll.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_localMusicFragment)
        }
        home_local_music_love_ll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fondListFragment)
        }

        recyclerView.layoutManager = LinearLayoutManager(context!!, RecyclerView.VERTICAL,false)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        var data = homeViewModel.getSongList()
        val myAdapter = MyAdapter(data,
            MyAdapter.OnClickEvent {position ->  //点击歌单跳转操作
                val songListId = data[position].id
                val bundle = Bundle()
                bundle.putString("listId", songListId)
                findNavController().navigate(R.id.action_homeFragment_to_songInListFragment,bundle)
            },
            MyAdapter.OnClickEvent {position -> //点击删除操作,position是所删除的歌单序号
                //data = listOP.deleteList(position)
                homeViewModel.deleteSongList(data[position].id!!,position )
                data = homeViewModel.getSongList()
            })
        recyclerView.adapter = myAdapter

        newList.setOnClickListener {
            val editText = EditText(context!!)
            AlertDialog.Builder(context!!)
                .setTitle("新建歌单")
                .setView(editText)
                .setNegativeButton("取消",null)
                .setPositiveButton("创建", DialogInterface.OnClickListener{ dialogInterface, ii ->//点击创建按钮事件
                    //data = listOP.addMusicList(listName.text.toString())
                    var id = 0
                    if(data.isEmpty()){ id = 2}
                    else {
                        id = data[data.size-1].id.toInt() + 1
                    }
                    val songlist = Songlist(id.toString(),editText.text.toString())
                    homeViewModel.insertSongList(songlist)
                    data = homeViewModel.getSongList()
                })
            .show()
        }
        homeViewModel.songList.observe(this, Observer {
            val myAdapter = MyAdapter(it,
                MyAdapter.OnClickEvent {position ->  //点击歌单跳转操作
                    val songListId = data[position].id
                    val bundle = Bundle()
                    bundle.putString("listId", songListId)
                    bundle.putString("songListName", data[position].name)
                    findNavController().navigate(R.id.action_homeFragment_to_songInListFragment,bundle)
                },
                MyAdapter.OnClickEvent {position -> //点击删除操作,position是所删除的歌单序号
                    //data = listOP.deleteList(position)
                    homeViewModel.deleteSongList(data[position].id, position)
                    data = homeViewModel.getSongList()
                })
            recyclerView.adapter = myAdapter
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val assets =context!!.assets
        super.onActivityCreated(savedInstanceState)
        val fromAsset = Typeface.createFromAsset(assets, "fonts/tff1.ttf");
        home_local_music_tv.typeface = fromAsset
        home_my_love_music_tv.typeface = fromAsset
        newList.typeface=fromAsset
//        local_music_name.typeface = fromAsset
//        local_music_singer.typeface = fromAsset
//        singer_name.typeface = fromAsset
    }


}
