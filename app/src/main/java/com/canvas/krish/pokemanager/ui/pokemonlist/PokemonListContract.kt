package com.canvas.krish.pokemanager.ui.pokemonlist

import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.ui.base.BaseLcPresenter
import com.canvas.krish.pokemanager.ui.base.BaseLcView

/**
 * Created by Krishna Chaitanya Kandula on 9/17/2017.
 */
interface PokemonListContract {

    interface View : BaseLcView<List<PokemonListResult>> {

        fun updateData(additionalPokemonListResults: List<PokemonListResult>)
        fun changeToolbarColor(color: Int)
        fun showPokemonDetail(id: Int)
    }

    interface Presenter : BaseLcPresenter {

        fun getAdditionalData(offset: Int, limit: Int)
        fun onScroll(firstItemVisiblePosition: Int, lastItemVisiblePosition: Int, totalItemCount: Int)
        fun onClickPokemon(id: Int)
    }
}
