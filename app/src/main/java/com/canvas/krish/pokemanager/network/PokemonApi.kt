package com.canvas.krish.pokemanager.network

import com.canvas.krish.pokemanager.data.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
interface PokemonApi {

    @GET("/api/v2/pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>
}