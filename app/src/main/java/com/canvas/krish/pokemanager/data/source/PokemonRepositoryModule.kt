package com.canvas.krish.pokemanager.data.source

import android.content.Context
import com.amazonaws.services.s3.AmazonS3
import com.canvas.krish.pokemanager.app.Constants
import com.canvas.krish.pokemanager.app.PokeManagerAppModule
import com.canvas.krish.pokemanager.network.NetworkModule
import com.canvas.krish.pokemanager.network.PokemonApi
import com.canvas.krish.pokemanager.scopes.ApplicationScoped
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
@Module(includes = arrayOf(NetworkModule::class, PokeManagerAppModule::class))
class PokemonRepositoryModule {

    @Provides
    @ApplicationScoped
    fun provideImageUrlCache(): Cache<Int, String> {
        val expiryHours: Long = 2
        return CacheBuilder.newBuilder()
                .maximumSize(Constants.NUMBER_OF_POKEMON.toLong())
                .expireAfterWrite(expiryHours, TimeUnit.HOURS)
                .build<Int, String>()
    }

    @Provides
    @ApplicationScoped
    fun providePokemonRepository(pokemonApi: PokemonApi,
                                 context: Context,
                                 amazonS3: AmazonS3,
                                 imageUrlCache: Cache<Int, String>): PokemonRepository {

        return CachingPokemonRepository(pokemonApi, context, amazonS3, imageUrlCache)
    }
}