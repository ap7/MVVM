package com.softbankrobotics.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.softbankrobotics.mvvm.databinding.RecyclerViewAlbumBinding

class AlbumAdapter(val albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {


    override fun getItemCount() = albums.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recycler_view_album,
            parent,
            false)
        )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.recyclerViewAlbumBinding.album = albums[position]
    }


    inner class AlbumViewHolder(val recyclerViewAlbumBinding: RecyclerViewAlbumBinding) :
        RecyclerView.ViewHolder(recyclerViewAlbumBinding.root)
}