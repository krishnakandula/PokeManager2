package com.canvas.krish.pokemanager.app

import android.app.Application

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
    }
}