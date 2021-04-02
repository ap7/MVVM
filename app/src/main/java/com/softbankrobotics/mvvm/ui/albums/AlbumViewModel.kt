package com.softbankrobotics.mvvm.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softbankrobotics.mvvm.data.models.Album
import com.softbankrobotics.mvvm.data.repositories.AlbumRepository
import com.softbankrobotics.mvvm.util.Coroutines
import kotlinx.coroutines.Job

// AlbumViewModel communicate with repository
class AlbumViewModel(private val repository: AlbumRepository) : ViewModel() {

    private lateinit var job: Job

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    fun getAlbums() {
        job = Coroutines.ioThenMAin(
            { repository.getAlbums() },
            { _albums.value = it }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}