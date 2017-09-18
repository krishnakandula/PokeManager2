package com.canvas.krish.pokemanager.app

import com.canvas.krish.pokemanager.data.source.PokemonRepository
import com.canvas.krish.pokemanager.data.source.PokemonRepositoryModule
import com.canvas.krish.pokemanager.scopes.ApplicationScoped
import dagger.Component

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
@ApplicationScoped
@Component(modules = arrayOf(PokeManagerAppModule::class, PokemonRepositoryModule::class))
interface PokeManagerAppComponent {
    fun getPokemonRepository(): PokemonRepository
}