package com.canvas.krish.pokemanager.ui.pokemondetail

import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.source.PokemonRepository
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Krishna Chaitanya Kandula on 9/21/17.
 */
class PokemonDetailPresenter @Inject constructor(private val view: PokemonDetailContract.View,
                                                 @Named("PokemonDetail.PokemonId") val pokemonId: Int,
                                                 private val pokemonRepository: PokemonRepository) : PokemonDetailContract.Presenter {

    override fun start() {
        var existingData: Pokemon? = view.getExistingData()
        if(existingData == null) {
            getData()
        }
    }

    override fun getData() {
    }

    override fun onRefresh() {

    }
}