package com.canvas.krish.pokemanager.data.source

import android.content.Context
import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.network.PokemonApi
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
class CachingPokemonRepository(private val pokemonApi: PokemonApi, private val context: Context) : PokemonRepository {
    //TODO: Implement caching

    companion object {
        private val INITIAL_DATA_PATH: String = "initial_data.json"
    }

    private var cachedPokemonList: MutableList<PokemonListResult> = mutableListOf()

    override fun getPokemonList(offset: Int, limit: Int, onSuccess: (List<PokemonListResult>) -> Unit, onError: (t: Throwable?) -> Unit) {
        if (cachedPokemonList.isEmpty()) {
            TODO("Parse initial_data.json to retrieve pokemon list data")
            try {
                cachedPokemonList = parseInitialData(context)
            }
        }

        if (offset < 0) return onError(IndexOutOfBoundsException("Invalid offset given"))

        //Return empty list if offset is > number of elements
        if (offset > cachedPokemonList.size) return onSuccess(listOf())
        val range: Int = if (offset + limit > cachedPokemonList.size - 1) cachedPokemonList.size - 1 else offset + limit
        onSuccess(cachedPokemonList.subList(offset, range).toList())
    }

    private fun parseInitialData(context: Context): JSONArray? {
        val inputStream: InputStream = context.assets.open(INITIAL_DATA_PATH)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val jsonData = String(buffer)
        return JSONArray(jsonData)
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