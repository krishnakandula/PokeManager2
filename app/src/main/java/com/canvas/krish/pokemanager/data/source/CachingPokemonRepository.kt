package com.canvas.krish.pokemanager.data.source

import android.content.Context
import android.util.Log
import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.canvas.krish.pokemanager.app.Constants
import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.data.models.Type
import com.canvas.krish.pokemanager.network.PokemonApi
import com.google.common.cache.Cache
import io.reactivex.Single
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.util.*

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
class CachingPokemonRepository(private val pokemonApi: PokemonApi,
                               private val context: Context,
                               private val amazonS3: AmazonS3,
                               private val cachedImageUrls: Cache<Int, String>) : PokemonRepository {

    companion object {
        private val INITIAL_DATA_PATH: String = "initial_data.json"
        private val LOG_TAG: String = CachingPokemonRepository::class.simpleName!!
    }

    private var cachedPokemonList: MutableList<PokemonListResult> = mutableListOf()

    override fun getPokemonList(offset: Int, limit: Int): Single<List<PokemonListResult>> {
        val single: Single<List<PokemonListResult>> = Single.create { emitter ->
            if (cachedPokemonList.isEmpty()) {
                try {
                    val jsonArray: JSONArray? = parseInitialData(context)
                    if (jsonArray != null) {
                        for (index in 0 until jsonArray.length()) {
                            val jsonObject: JSONObject = jsonArray.getJSONObject(index)
                            val id: Int = jsonObject.getInt("_id")

                            //Check if Pokemon contains second type
                            var type2: Type? = null
                            if(jsonObject.has("_type2")) {
                                type2 = Type.valueOf(jsonObject.getString("_type2").toUpperCase())
                            }

                            val pokemonlistResult: PokemonListResult = PokemonListResult(
                                    id,
                                    jsonObject.getString("_name").capitalize(),
                                    jsonObject.getString("_front_default_sprite_uri"),
                                    jsonObject.getString("_description"),
                                    generatePokemonImageUrl(id),
                                    Type.valueOf(jsonObject.getString("_type1").toUpperCase()),
                                    type2)
                            cachedPokemonList.add(pokemonlistResult)
                        }
                    }
                } catch (e: JSONException) {
                    Log.e(LOG_TAG, e.message)
                    emitter.onError(e)
                }
            }

            if (offset < 0) {
                val error: String = "Invalid offset given"
                Log.e(LOG_TAG, error)
                emitter.onError(IndexOutOfBoundsException(error))
            }

            //Return empty list if offset is > number of elements
            if (offset > cachedPokemonList.size) emitter.onSuccess(listOf())

            val range: Int = if (offset + limit > cachedPokemonList.size - 1) cachedPokemonList.size - 1 else offset + limit
            emitter.onSuccess(cachedPokemonList.subList(offset, range).toList())
        }

        return single
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

    private fun generatePokemonImageUrl(id: Int): String {
        var cachedUrl: String? = cachedImageUrls.getIfPresent(id)
        if(cachedUrl == null) {
            val expirationDate: Date = Date()
            val msec: Long = expirationDate.time + (1000 * 60 * 60)     //1 hour
            expirationDate.time = msec

            val presignedUrlRequest: GeneratePresignedUrlRequest = GeneratePresignedUrlRequest(
                    Constants.BUCKET_NAME,
                    "$id.png")
                    .withMethod(HttpMethod.GET)
                    .withExpiration(expirationDate)

            cachedUrl = amazonS3.generatePresignedUrl(presignedUrlRequest).toString()
            cachedImageUrls.put(id, cachedUrl)
        }

        return cachedUrl
    }
}
