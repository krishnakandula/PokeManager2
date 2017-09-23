package com.canvas.krish.pokemanager.ui.pokemondetail

import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.ui.base.BaseLcPresenter
import com.canvas.krish.pokemanager.ui.base.BaseLcView

/**
 * Created by Krishna Chaitanya Kandula on 9/20/2017.
 */
interface PokemonDetailContract {
    interface View : BaseLcView<Pokemon> {

        fun showPokemonListResultData(listResult: PokemonListResult)
        fun getExistingPokemonListResultData(): PokemonListResult?

    }

    interface Presenter : BaseLcPresenter {

        fun getPokemonListResultData()
    }
}