package com.canvas.krish.pokemanager.data.source

import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.network.PokemonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
class CachingPokemonRepository(private val pokemonApi: PokemonApi) : PokemonRepository {
    //TODO: Implement caching

    override fun getPokemonList(offset: Int, limit: Int, onSuccess: (List<PokemonListResult>) -> Unit, onError: (t: Throwable?) -> Unit) {
       TODO("Parse initial_data.json to retrieve pokemon list data")
    }

    override fun getPokemon(id: Int, onSuccess: (Pokemon) -> Unit, onError: (t: Throwable?) -> Unit) {
        pokemonApi.getPokemon(id).enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
                onError(t)
            }

            override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>?) {
                onSuccess(response!!.body()!!)
            }
        })
    }
}