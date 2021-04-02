package com.softbankrobotics.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class AlbumFragment : Fragment() {

    private lateinit var factory: AlbumsViewModelFactory
    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.album_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = AlbumApi()
        val repository = AlbumRepository(api)
        factory = AlbumsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(AlbumViewModel::class.java)
    }
}