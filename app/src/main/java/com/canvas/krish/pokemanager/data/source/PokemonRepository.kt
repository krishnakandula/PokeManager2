package com.canvas.krish.pokemanager.data.source

import com.canvas.krish.pokemanager.data.models.PokemonListResult

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
interface PokemonRepository {

    fun getPokemon(offset: Int, limit: Int, callback: (List<PokemonListResult>) -> Unit, onError: (t: Throwable?) -> Unit)



}