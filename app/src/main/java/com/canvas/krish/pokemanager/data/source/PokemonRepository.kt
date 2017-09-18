package com.canvas.krish.pokemanager.data.source

import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.models.PokemonListResult

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
interface PokemonRepository {

    fun getPokemonList(offset: Int, limit: Int, onSuccess: (List<PokemonListResult>) -> Unit, onError: (t: Throwable?) -> Unit)

    fun getPokemon(id: Int, onSuccess: (Pokemon) -> Unit, onError: (t: Throwable?) -> Unit)

}