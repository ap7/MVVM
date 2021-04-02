package com.softbankrobotics.mvvm.data.repositories

import com.softbankrobotics.mvvm.data.network.AlbumApi

class AlbumRepository(private val api: AlbumApi) : SafeApiRequest() {

    suspend fun getAlbums() = apiRequest { api.getAlbums() }

}