package com.main.musicplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_list.view.*

class MusicAdapter (private var musics: MutableList<Music>):// new var and inside it list of music(data class)
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {//when needed provide a view holder
        return MusicViewHolder(
            LayoutInflater.from(parent.context)
                .inflate( //inflating the viewholder from the recyler list
                    R.layout.recycler_list,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val currentMusic = musics.get(position)
        holder.itemView.textView.text = currentMusic.music

    }

    override fun getItemCount(): Int {
        return musics.size
    }
}
