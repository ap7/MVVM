package com.softbankrobotics.mvvm.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.softbankrobotics.mvvm.R
import com.softbankrobotics.mvvm.data.network.AlbumApi
import com.softbankrobotics.mvvm.data.repositories.AlbumRepository
import kotlinx.android.synthetic.main.album_fragment.*

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

        viewModel.getAlbums()
        viewModel.albums.observe(viewLifecycleOwner, Observer { albums ->
            recycler_view_album.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = AlbumAdapter(albums)
            }
        })
    }
}