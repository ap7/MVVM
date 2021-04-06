package com.softbankrobotics.mvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softbankrobotics.mvvm.data.models.Album

@Database(
    entities = [Album::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getAlbumDao() : AlbumDao
}