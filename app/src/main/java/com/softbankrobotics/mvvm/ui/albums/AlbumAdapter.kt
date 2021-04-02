package com.softbankrobotics.mvvm.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.softbankrobotics.mvvm.R
import com.softbankrobotics.mvvm.data.models.Album
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
        /*
         Glide.with(holder.recyclerViewAlbumBinding.imageView)
            .load(holder.recyclerViewAlbumBinding.album.thumbnailUrl)
            .into(holder.recyclerViewAlbumBinding.imageView)
         */
    }


    inner class AlbumViewHolder(val recyclerViewAlbumBinding: RecyclerViewAlbumBinding) :
        RecyclerView.ViewHolder(recyclerViewAlbumBinding.root)
}