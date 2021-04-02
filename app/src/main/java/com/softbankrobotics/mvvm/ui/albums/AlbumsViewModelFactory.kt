package com.softbankrobotics.mvvm.ui.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.softbankrobotics.mvvm.data.repositories.AlbumRepository

class AlbumsViewModelFactory(
    private val repository: AlbumRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumViewModel(repository) as T
    }
}