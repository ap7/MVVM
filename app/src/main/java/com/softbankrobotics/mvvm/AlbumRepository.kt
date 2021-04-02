package com.softbankrobotics.mvvm

class AlbumRepository(private val api: AlbumApi) : SafeApiRequest() {

    suspend fun getAlbums() = apiRequest { api.getAlbums() }

}