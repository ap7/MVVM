package com.softbankrobotics.mvvm.data.network

import com.softbankrobotics.mvvm.data.models.Album
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {

    @GET("technical-test.json")
    suspend fun getAlbums(): Response<List<Album>>
}