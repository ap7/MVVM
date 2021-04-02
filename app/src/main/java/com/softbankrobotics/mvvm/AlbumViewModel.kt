package com.softbankrobotics.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// AlbumViewModel communicate with repository
class AlbumViewModel(private val repository: AlbumRepository) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()

    val albums : LiveData<List<Album>>
        get() = _albums

    suspend fun getAlbums() {
        val albums = repository.getAlbums()
        _albums.value = albums
    }
}