package com.canvas.krish.pokemanager.ui.pokemondetail

import com.canvas.krish.pokemanager.scopes.ActivityScoped
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Krishna Chaitanya Kandula on 9/21/17.
 */
@Module
class PokemonDetailModule(private val view: PokemonDetailContract.View,
                          private val pokemonId: Int) {

    @Provides
    @ActivityScoped
    fun providePokemonDetailContractView(): PokemonDetailContract.View = view

    @Provides
    @ActivityScoped
    fun providePokemonDetailContractPresenter(presenter: PokemonDetailPresenter): PokemonDetailContract.Presenter = presenter

    @Provides
    @ActivityScoped
    @Named("PokemonDetail.PokemonId")
    fun providePokemonId(): Int = pokemonId
}