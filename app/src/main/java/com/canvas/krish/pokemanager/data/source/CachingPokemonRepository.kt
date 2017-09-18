package com.canvas.krish.pokemanager.data.source

import com.canvas.krish.pokemanager.data.models.PokemonList
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.network.PokemonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
class CachingPokemonRepository(val pokemonApi: PokemonApi) : PokemonRepository {
    //TODO: Implement caching

    override fun getPokemonList(offset: Int, limit: Int, onSuccess: (PokemonList) -> Unit, onError: (t: Throwable?) -> Unit) {
        pokemonApi.getPokemonList(offset, limit).enqueue(object : Callback<PokemonList> {

            override fun onResponse(call: Call<PokemonList>?, response: Response<PokemonList>?) {
                if(response != null && response.isSuccessful) onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<PokemonList>?, t: Throwable?) {
                //TODO: Show error
            }
        })
    }

    override fun getPokemon(offset: Int, limit: Int, callback: (List<PokemonListResult>) -> Unit, onError: (t: Throwable?) -> Unit) {

    }
}