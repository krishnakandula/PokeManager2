package com.canvas.krish.pokemanager.ui.pokemonlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.itemview_pokemon_list.view.*

/**
 * Created by Krishna Chaitanya Kandula on 9/18/17.
 */
class PokemonListViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    fun onBind(pokemonListResult: PokemonListResult) {
        itemView.pokemonIdTextView_PokemonListItemView.text = "${pokemonListResult.id}"
        itemView.pokemonNameTextView_pokemonListItemView.text = pokemonListResult.name
        itemView.pokemonDescriptionTextView_pokemonListItemView.text = pokemonListResult.description

        Picasso.with(context)
                .load(pokemonListResult.imageUrl)
                .resizeDimen(R.dimen.minPokemonImageViewDiameter, R.dimen.minPokemonImageViewDiameter)
                .centerInside()
                .into(itemView.pokemonImageView_pokemonListItemView)
    }
}