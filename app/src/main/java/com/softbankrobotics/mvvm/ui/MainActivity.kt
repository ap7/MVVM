package com.softbankrobotics.mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.softbankrobotics.mvvm.R
import com.softbankrobotics.mvvm.data.network.AlbumApi
import com.softbankrobotics.mvvm.data.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = AlbumRepository(AlbumApi())

        GlobalScope.launch(Dispatchers.Main) {
            val albums = repository.getAlbums()
            Log.d("TAG", albums.toString())
        }

    }
}