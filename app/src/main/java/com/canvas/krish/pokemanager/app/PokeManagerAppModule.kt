package com.canvas.krish.pokemanager.app

import android.content.Context
import com.canvas.krish.pokemanager.scopes.ApplicationScoped
import dagger.Module
import dagger.Provides

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
@Module(includes = arrayOf(PokeManagerAppModule::class))
class PokeManagerAppModule(private val application: PokeManagerApplication) {
    
    @Provides
    @ApplicationScoped
    fun provideApplicationContext(): Context {
        return application
    }
}