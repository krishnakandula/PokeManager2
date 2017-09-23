package com.canvas.krish.pokemanager.ui.pokemoninfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.ui.pokemondetail.PokemonDetailFragmentContract

class PokemonInfoFragment : Fragment(), PokemonDetailFragmentContract {

    companion object {
        private val LOG_TAG: String = PokemonInfoFragment::class.simpleName!!

        fun newInstance(pokemon: Pokemon?, pokemonListResult: PokemonListResult?): PokemonInfoFragment {
            val fragment = PokemonInfoFragment()
            val args = Bundle()
            fragment.arguments = args

            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_pokemon_info, container, false)
        return view
    }

    override fun setData(pokemon: Pokemon, pokemonListResult: PokemonListResult) {

    }

    override fun getTitle(): String = "Info"
}
