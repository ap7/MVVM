package com.softbankrobotics.mvvm

import android.app.Application
import androidx.room.Room
import com.softbankrobotics.mvvm.data.PreferenceProvider
import com.softbankrobotics.mvvm.data.db.AppDatabase
import com.softbankrobotics.mvvm.data.network.AlbumApi
import com.softbankrobotics.mvvm.data.network.AlbumManager
import com.softbankrobotics.mvvm.data.network.NetworkConnectionInterceptor
import com.softbankrobotics.mvvm.data.repositories.AlbumRepository
import com.softbankrobotics.mvvm.ui.albums.AlbumsViewModelFactory
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MVVMApplication : Application(), KodeinAware {
    /**
     * A Kodein Aware class must be within reach of a [Kodein] object.
     *
     * LOOSELY COUPLED CODE
     * EASY TO TESTING AFTER
     */
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }

        bind() from singleton {
            val networkConnectionInterceptor: NetworkConnectionInterceptor = instance()
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            Retrofit.Builder()
                .client(okkHttpclient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://static.leboncoin.fr/img/shared/")
                .build()
                .create(AlbumApi::class.java)
        }

        bind() from singleton {
            Room.databaseBuilder(
                instance(),
                AppDatabase::class.java,
                "AlbumDatabase.db"
            ).build()
        }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { AlbumRepository(instance(), instance(), instance()) }
        bind() from singleton { AlbumManager(instance()) }
        bind() from singleton { AlbumsViewModelFactory(instance()) }
    }
}