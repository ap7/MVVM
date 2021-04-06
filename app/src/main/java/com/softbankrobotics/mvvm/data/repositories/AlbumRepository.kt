package com.softbankrobotics.mvvm.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.softbankrobotics.mvvm.data.PreferenceProvider
import com.softbankrobotics.mvvm.data.db.AppDatabase
import com.softbankrobotics.mvvm.data.models.Album
import com.softbankrobotics.mvvm.data.network.AlbumManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

@RequiresApi(Build.VERSION_CODES.O)
class AlbumRepository(
    private val api: AlbumManager,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAlbums(): LiveData<List<Album>> {
        return withContext(IO) {
            fetchAlbums()
            db.getAlbumDao().getAlbum()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchAlbums() {
        val lastSavedAt = prefs.getLastSavedAt()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            try {
                val response = api.getAlbums()
                saveAlbums(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun saveAlbums(album: List<Album>) {
        prefs.savelastSavedAt(LocalDateTime.now().toString())
        db.getAlbumDao().insertAllAlbums(album)
    }
}