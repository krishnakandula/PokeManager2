package com.canvas.krish.pokemanager.data.source

import android.content.Context
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
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
    fun providePokemonRepository(pokemonApi: PokemonApi,
                                 context: Context,
                                 amazonS3: AmazonS3): PokemonRepository {
        return CachingPokemonRepository(pokemonApi, context, amazonS3)
    }
}