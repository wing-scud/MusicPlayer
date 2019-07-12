package com.hfut.music

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.Song
import com.example.projectprepare1.ui.localmusic.MyClickListener
import kotlinx.android.synthetic.main.music_item.view.*

class MusicAdapter (
    val context: Context,
    val itemLayout: Int,
    val data: List<Song>,
    val musicClickListener: MyClickListener
):RecyclerView.Adapter<MusicAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(itemLayout,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.musicName.text = data[position].song
        holder.singerName.text = data[position].singer
        holder.itemView.setOnClickListener {
            musicClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var musicName: TextView = itemView.local_music_name
        var singerName: TextView = itemView.local_music_singer
    }
}