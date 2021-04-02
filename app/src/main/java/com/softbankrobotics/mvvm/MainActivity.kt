package com.softbankrobotics.mvvm

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            Toast.makeText(this@MainActivity, albums.toString(), Toast.LENGTH_LONG).show()
            Log.d("TAG", albums.toString())
        }

    }
}