package com.canvas.krish.pokemanager.app

import android.app.Application

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
class PokeManagerApplication : Application() {

    val component: PokeManagerAppComponent by lazy {
        DaggerPokeManagerAppComponent.builder()
                .pokeManagerAppModule(PokeManagerAppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}