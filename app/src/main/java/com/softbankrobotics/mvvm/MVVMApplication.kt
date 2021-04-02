package com.softbankrobotics.mvvm

import android.app.Application
import com.softbankrobotics.mvvm.data.network.AlbumApi
import com.softbankrobotics.mvvm.data.network.NetworkConnectionInterceptor
import com.softbankrobotics.mvvm.data.repositories.AlbumRepository
import com.softbankrobotics.mvvm.ui.albums.AlbumsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {
    /**
     * A Kodein Aware class must be within reach of a [Kodein] object.
     *
     * LOOSELY COUPLKED CODE
     * EASY TO TESTING AFTER
     */
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { AlbumApi(instance()) }
        bind() from singleton { AlbumRepository(instance()) }
        bind() from singleton { AlbumsViewModelFactory(instance()) }
    }
}