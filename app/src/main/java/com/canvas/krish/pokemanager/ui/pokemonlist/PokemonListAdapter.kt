package com.canvas.krish.pokemanager.ui.pokemonlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.data.models.PokemonListResult

/**
 * Created by Krishna Chaitanya Kandula on 9/18/2017.
 */
class PokemonListAdapter(private val context: Context, private val onClickListener: (id: Int) -> Unit) : RecyclerView.Adapter<PokemonListViewHolder>() {

    var data: MutableList<PokemonListResult> = mutableListOf()
        set(newData) {
            val range: Int = data.size
            data.clear()
            notifyItemRangeRemoved(0, range)
            data.addAll(newData)
            notifyItemRangeInserted(0, data.size)

        }

    fun updateData(additionalData: List<PokemonListResult>) {
        val start: Int = data.size
        data.addAll(additionalData)
        notifyItemRangeInserted(start, data.size)
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PokemonListViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val itemView: View = layoutInflater.inflate(R.layout.itemview_pokemon_list, parent, false)

        return PokemonListViewHolder(itemView, context, onClickListener)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder?, position: Int) {
        holder?.onBind(data[position])
    }

}
