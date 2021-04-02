package com.softbankrobotics.mvvm.data.network

import com.softbankrobotics.mvvm.data.models.Album
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AlbumApi {

    @GET("technical-test.json")
    suspend fun getAlbums(): Response<List<Album>>

    companion object {

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): AlbumApi {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://static.leboncoin.fr/img/shared/")
                .build()
                .create(AlbumApi::class.java)
        }
    }
}