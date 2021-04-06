package com.softbankrobotics.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softbankrobotics.mvvm.data.models.Album

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAlbums(album: List<Album>)

    @Query("SELECT * FROM album")
    fun getAlbum() : LiveData<List<Album>>
}