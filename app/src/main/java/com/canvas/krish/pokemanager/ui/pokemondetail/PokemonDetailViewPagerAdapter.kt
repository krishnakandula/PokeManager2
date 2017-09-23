package com.canvas.krish.pokemanager.ui.pokemondetail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.ui.pokemoninfo.PokemonInfoFragment

/**
 * Created by Krishna Chaitanya Kandula on 9/23/2017.
 */
class PokemonDetailViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        private val numItems: Int = 1
    }

    private val detailFragments: List<PokemonDetailFragmentContract> = listOf(PokemonInfoFragment.newInstance(null, null))

    override fun getItem(position: Int): Fragment = detailFragments[position] as Fragment

    override fun getCount(): Int = numItems

    override fun getPageTitle(position: Int): CharSequence = detailFragments[position].getTitle()

    fun setDetailFragmentData(pokemon: Pokemon, pokemonListResult: PokemonListResult) { detailFragments.forEach({it.setData(pokemon, pokemonListResult)}) }
}
