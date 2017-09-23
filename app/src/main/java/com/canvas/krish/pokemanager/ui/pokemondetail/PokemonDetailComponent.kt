package com.canvas.krish.pokemanager.ui.pokemondetail

import com.canvas.krish.pokemanager.app.PokeManagerAppComponent
import com.canvas.krish.pokemanager.scopes.ActivityScoped
import dagger.Component

/**
 * Created by Krishna Chaitanya Kandula on 9/21/17.
 */
@ActivityScoped
@Component(dependencies = arrayOf(PokeManagerAppComponent::class), modules = arrayOf(PokemonDetailModule::class))
interface PokemonDetailComponent {
    fun inject(target: PokemonDetailActivity)
}