package com.canvas.krish.pokemanager.app

import android.app.Application
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
class PokeManagerApplication : Application() {

    lateinit var component: PokeManagerAppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerPokeManagerAppComponent.builder()
                .pokeManagerAppModule(PokeManagerAppModule(this))
                .build()
        Picasso.setSingletonInstance(buildPicassoSingleton())
    }

    private fun buildPicassoSingleton(): Picasso {
        val builder: Picasso.Builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, 1024 * 100))
        val built: Picasso = builder.build()
        built.setIndicatorsEnabled(true)
        built.isLoggingEnabled = true

        return built
    }
}