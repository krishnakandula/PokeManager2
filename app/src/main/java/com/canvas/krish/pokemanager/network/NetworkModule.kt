package com.canvas.krish.pokemanager.network

import com.canvas.krish.pokemanager.app.Constants
import com.canvas.krish.pokemanager.scopes.ApplicationScoped
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
@Module
class NetworkModule {

    @Provides
    @Named("API_BASE_URL")
    @ApplicationScoped
    protected fun provideApiBaseUrl(): String = Constants.API_BASE_URL

    @Provides
    @ApplicationScoped
    protected fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @ApplicationScoped
    protected fun provideHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @ApplicationScoped
    protected fun provideRetrofit(@Named("API_BASE_URL") baseUrl: String, gsonConverterFactory: GsonConverterFactory, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient)
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @ApplicationScoped
    protected fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)
}