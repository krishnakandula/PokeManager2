package com.canvas.krish.pokemanager.ui.pokemondetail

import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.models.PokemonListResult

/**
 * Created by Krishna Chaitanya Kandula on 9/23/2017.
 */
interface PokemonDetailFragmentContract {
    fun setData(pokemon: Pokemon, pokemonListResult: PokemonListResult)

    fun getTitle(): String
}