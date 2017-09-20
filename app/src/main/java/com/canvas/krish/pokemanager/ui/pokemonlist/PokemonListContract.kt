package com.canvas.krish.pokemanager.ui.pokemonlist

import com.canvas.krish.pokemanager.data.models.PokemonListResult

/**
 * Created by Krishna Chaitanya Kandula on 9/17/2017.
 */
interface PokemonListContract {

    interface View {
        fun showLoading()
        fun stopLoading()
        fun setData(pokemonListResults: List<PokemonListResult>)
        fun updateData(additionalPokemonListResults: List<PokemonListResult>)
        fun getExistingData(): List<PokemonListResult>?
        fun showErrorLoadingData()
        fun changeToolbarColor(color: String)
    }

    interface Presenter {
        fun start()
        fun getData()
        fun getAdditionalData(offset: Int, limit: Int)
        fun onRefresh()
        fun onScroll(firstItemVisiblePosition: Int, lastItemVisiblePosition: Int, totalItemCount: Int)
    }
}