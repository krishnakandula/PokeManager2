package com.canvas.krish.pokemanager.ui.pokemonlist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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

        Picasso.with(context)
                .load(pokemonListResult.imageUrl)
                .resizeDimen(R.dimen.minPokemonImageViewDiameter, R.dimen.minPokemonImageViewDiameter)
                .centerInside()
                .into(itemView.pokemonImageView_pokemonListItemView, object: Callback {
                    override fun onSuccess() {
                        generateColorPalette((((itemView.pokemonImageView_pokemonListItemView as ImageView).drawable) as BitmapDrawable).bitmap)
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object: SingleObserver<Palette> {
                                    override fun onSuccess(palette: Palette?) {
                                        if(palette != null) {
                                            val defaultColor: Int = context.getColor(R.color.primary)
                                            itemView.cardview_PokemonListItemView.setCardBackgroundColor(palette.getLightMutedColor(defaultColor))
                                        }
                                    }

                                    override fun onSubscribe(d: Disposable?) {
                                        //Not implemented
                                    }

                                    override fun onError(e: Throwable?) {
                                        Log.e(LOG_TAG, e!!.message, e)
                                    }
                                })
                    }

                    override fun onError() {
                        Log.e(LOG_TAG, "Unable to load image into ImageView")
                    }
                })
    }

    private fun generateColorPalette(bitmap: Bitmap): Single<Palette> {
        return Single.create { emitter ->
            val palette = Palette.from(bitmap).generate()
            emitter.onSuccess(palette)
        }
    }
}
