package com.softbankrobotics.mvvm.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.softbankrobotics.mvvm.R
import com.softbankrobotics.mvvm.data.models.Album
import com.softbankrobotics.mvvm.util.Coroutines
import kotlinx.android.synthetic.main.album_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class AlbumFragment : Fragment(), RecyclerViewClickListener, KodeinAware {

    /**
     * A Kodein Aware class must be within reach of a [Kodein] object.
     */
    override val kodein by kodein()
    private val factory: AlbumsViewModelFactory by instance()

    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.album_fragment, container, false)
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory).get(AlbumViewModel::class.java)

        Coroutines.main {
            viewModel.albums.await().observe(viewLifecycleOwner, { albums ->
                recycler_view_album.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = AlbumAdapter(albums, this)
                    it.adapter?.stateRestorationPolicy = PREVENT_WHEN_EMPTY
                }
            })
        }
    }

    override fun onItemClick(view: View, album: Album) {
        when (view.id) {
            R.id.imageView -> {
                Toast.makeText(requireContext(), "Image clicked", Toast.LENGTH_LONG).show()
            }
        }
    }
}