package com.example.projectprepare1.ui.singer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectprepare1.ui.localmusic.MyClickListener
import com.example.projectprepare1.R
import kotlinx.android.synthetic.main.singer_item.view.*

class SingerAdapter (
    val context: Context,
    val itemLayout: Int,
    val data: Array<String>,
    val singerClickListener: MyClickListener
):RecyclerView.Adapter<SingerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(itemLayout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.singerIcon.setImageResource(R.drawable.singer)
        holder.singerName.text = data[position]
        holder.itemView.setOnClickListener {
            singerClickListener.onClick(position)
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var singerName: TextView = itemView.singer_name
        var singerIcon: ImageView = itemView.singer_icon
    }
}
