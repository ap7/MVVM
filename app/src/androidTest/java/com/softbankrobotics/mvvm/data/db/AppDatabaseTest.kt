package com.softbankrobotics.mvvm.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.softbankrobotics.mvvm.data.models.Album
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {

    private lateinit var db: AppDatabase
    private lateinit var albumDao: AlbumDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        albumDao = db.getAlbumDao()
    }

    @After
    public override fun tearDown() {
        db.close()
    }

    @Test
    fun writeAndReadData() = runBlocking {
        val album = listOf(
            Album("albumId",
                "7",
                "https://via.placeholder.com/150/92c952",
                "testAlbum_1,",
                "https://via.placeholder.com/150/92c952"),
            Album("albumId",
                "1",
                "https://via.placeholder.com/150/92c952",
                "testAlbum_2,",
                "https://via.placeholder.com/150/92c952"))


        albumDao.insertAllAlbums(album)

        val albums = albumDao

        Assert.assertNotNull(albums)
    }
}