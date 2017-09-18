package com.canvas.krish.pokemanager.ui.pokemonlist

import com.canvas.krish.pokemanager.scopes.ActivityScoped
import dagger.Module
import dagger.Provides

/**
 * Created by Krishna Chaitanya Kandula on 9/17/2017.
 */
@Module
class PokemonListModule(private val view : PokemonListContract.View) {

    @Provides
    @ActivityScoped
    fun providePokemonListContractView(): PokemonListContract.View = view

    @Provides
    @ActivityScoped
    fun providePokemonListContractPresenter(presenter: PokemonListPresenter): PokemonListContract.Presenter = presenter

}