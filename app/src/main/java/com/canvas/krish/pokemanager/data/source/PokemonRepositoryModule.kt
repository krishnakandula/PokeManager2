package com.canvas.krish.pokemanager.data.source

import android.content.Context
import com.canvas.krish.pokemanager.app.PokeManagerAppModule
import com.canvas.krish.pokemanager.network.NetworkModule
import com.canvas.krish.pokemanager.network.PokemonApi
import com.canvas.krish.pokemanager.scopes.ApplicationScoped
import dagger.Module
import dagger.Provides

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
@Module(includes = arrayOf(NetworkModule::class, PokeManagerAppModule::class))
class PokemonRepositoryModule {

    @Provides
    @ApplicationScoped
    fun providePokemonRepository(pokemonApi: PokemonApi, context: Context): PokemonRepository = CachingPokemonRepository(pokemonApi, context)
}