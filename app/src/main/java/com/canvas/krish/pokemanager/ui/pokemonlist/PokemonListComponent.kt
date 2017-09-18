package com.canvas.krish.pokemanager.ui.pokemonlist

import com.canvas.krish.pokemanager.app.PokeManagerAppComponent
import com.canvas.krish.pokemanager.scopes.ActivityScoped
import dagger.Component

/**
 * Created by Krishna Chaitanya Kandula on 9/17/2017.
 */
@ActivityScoped
@Component(dependencies = arrayOf(PokeManagerAppComponent::class), modules = arrayOf(PokemonListModule::class))
interface PokemonListComponent {
    fun inject(target: PokemonListActivity)
}