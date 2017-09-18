package com.canvas.krish.pokemanager.data.source

import com.canvas.krish.pokemanager.network.NetworkModule
import com.canvas.krish.pokemanager.network.PokemonApi
import com.canvas.krish.pokemanager.scopes.ApplicationScoped
import dagger.Module
import dagger.Provides

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
@Module(includes = arrayOf(NetworkModule::class))
class PokemonRepositoryModule {

    @Provides
    @ApplicationScoped
    fun providePokemonRepository(pokemonApi: PokemonApi): PokemonRepository = CachingPokemonRepository(pokemonApi)
}