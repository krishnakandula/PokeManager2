package com.canvas.krish.pokemanager.ui.pokemonlist

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
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

    companion object {
        private val LOG_TAG: String = PokemonListViewHolder::class.simpleName!!
    }

    fun onBind(pokemonListResult: PokemonListResult) {
        itemView.pokemonIdTextView_PokemonListItemView.text = "#${pokemonListResult.id}"
        itemView.pokemonNameTextView_pokemonListItemView.text = pokemonListResult.name
        itemView.pokemonDescriptionTextView_pokemonListItemView.text = pokemonListResult.description
        itemView.pokemonType1TextView_pokemonListItemView.text = pokemonListResult.type1.toString().toLowerCase().capitalize()
        if(pokemonListResult.type2 != null) {
            itemView.pokemonType2TextView_pokemonListItemView.text = pokemonListResult.type2.toString().toLowerCase().capitalize()
            itemView.pokemonType2TextView_pokemonListItemView.visibility = View.VISIBLE
        } else {
            itemView.pokemonType2TextView_pokemonListItemView.text = ""
            itemView.pokemonType2TextView_pokemonListItemView.visibility = View.GONE
        }

        itemView.cardview_PokemonListItemView.setCardBackgroundColor(Color.parseColor(pokemonListResult.type1.color))
        changeTextColors(context.getColor(R.color.md_white_1000))
        changeOutlineColors(context.getColor(R.color.md_white_1000))

        Picasso.with(context)
                .load(pokemonListResult.imageUrl)
                .resizeDimen(R.dimen.minPokemonImageViewDiameter, R.dimen.minPokemonImageViewDiameter)
                .centerInside()
                .into(itemView.pokemonImageView_pokemonListItemView)
    }

    private fun changeTextColors(color: Int) {
        itemView.pokemonDescriptionTextView_pokemonListItemView.setTextColor(color)
        itemView.pokemonIdTextView_PokemonListItemView.setTextColor(color)
        itemView.pokemonNameTextView_pokemonListItemView.setTextColor(color)
        itemView.pokemonType1TextView_pokemonListItemView.setTextColor(color)
        itemView.pokemonType2TextView_pokemonListItemView.setTextColor(color)
    }

    private fun changeOutlineColors(color: Int) {
        (itemView.pokemonDescriptionTextView_pokemonListItemView.background as GradientDrawable).setStroke(
                context.resources.getDimension(R.dimen.defaultOutlineStrokeWidth).toInt(),
                color)
        (itemView.pokemonType1TextView_pokemonListItemView.background as GradientDrawable).setStroke(
                context.resources.getDimension(R.dimen.defaultOutlineStrokeWidth).toInt(),
                color)
        (itemView.pokemonType2TextView_pokemonListItemView.background as GradientDrawable).setStroke(
                context.resources.getDimension(R.dimen.defaultOutlineStrokeWidth).toInt(),
                color)
    }
}
