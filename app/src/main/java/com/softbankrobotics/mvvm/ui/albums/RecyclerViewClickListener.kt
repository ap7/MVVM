package com.softbankrobotics.mvvm.ui.albums

import android.view.View
import com.softbankrobotics.mvvm.data.models.Album

interface RecyclerViewClickListener {
    fun onItemClick(view: View, album: Album)
}