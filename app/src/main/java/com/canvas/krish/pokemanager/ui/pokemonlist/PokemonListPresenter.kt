package com.canvas.krish.pokemanager.ui.pokemonlist

import com.canvas.krish.pokemanager.data.source.PokemonRepository
import javax.inject.Inject

/**
 * Created by Krishna Chaitanya Kandula on 9/17/2017.
 */
class PokemonListPresenter @Inject constructor(private val pokemonRepository: PokemonRepository, private val view: PokemonListContract.View): PokemonListContract.Presenter {

    companion object {
        private val RETRIEVAL_LIMIT: Int = 20
    }

    override fun start() {
        getData(0, RETRIEVAL_LIMIT, false)
    }

    override fun getData(offset: Int, limit: Int, refresh: Boolean) {
        view.showLoading()
        pokemonRepository.getPokemonList(offset, limit, {
            view.stopLoading()
            view.setData(it.results.toList())
        }, {
            view.stopLoading()
            view.showErrorLoadingData()
        })
    }

    override fun onRefresh() {
        getData(0, RETRIEVAL_LIMIT, false)
    }
}