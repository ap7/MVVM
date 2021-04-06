package com.softbankrobotics.mvvm.ui.albums

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.softbankrobotics.mvvm.data.repositories.AlbumRepository
import com.softbankrobotics.mvvm.util.lazyDeferred

// AlbumViewModel communicate with repository
@RequiresApi(Build.VERSION_CODES.O)
class AlbumViewModel(private val repository: AlbumRepository) : ViewModel() {

    val albums by lazyDeferred {
        repository.getAlbums()
    }
}