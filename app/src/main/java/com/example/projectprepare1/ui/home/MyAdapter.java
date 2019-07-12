package com.example.projectprepare1.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.roomwordssample.Songlist;
import com.example.projectprepare1.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {
    private List<Songlist> songList;
    private OnClickEvent onClickEvent;
    private OnClickEvent childOnClickEvent;

    public MyAdapter(List<Songlist> songList, OnClickEvent onClickEvent, OnClickEvent childOnClickEvent){
        this.songList = songList;
        this.onClickEvent = onClickEvent;
        this.childOnClickEvent = childOnClickEvent;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if(position <= getItemCount() - 1){
            holder.title.setText(songList.get(position).getName());
            holder.id = Integer.parseInt(songList.get(position).getId());
            holder.itemView.setOnClickListener(new onItemClickListener(position,onClickEvent));
            holder.itemView.findViewById(R.id.delete).setOnClickListener(new onItemClickListener(position,childOnClickEvent));
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }



    static class VH extends RecyclerView.ViewHolder{
        private TextView title;
        protected Integer id;
        public VH(View view){
            super(view);
            title = view.findViewById(R.id.item_listname);
        }
    }

    public class onItemClickListener implements View.OnClickListener{
        private OnClickEvent e;
        private int position;
        public onItemClickListener(int position, OnClickEvent e){
            this.position = position;
            this.e = e;
        }
        @Override
        public void onClick(View view) {
            e.ClickEvent(position);
        }
    }

    public interface OnClickEvent{
        void ClickEvent(int position);
    }
}

