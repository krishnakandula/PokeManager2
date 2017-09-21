package com.canvas.krish.pokemanager.ui.pokemonlist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.data.models.Type
import com.canvas.krish.pokemanager.data.source.CachingPokemonRepository
import com.google.gson.stream.JsonWriter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.itemview_pokemon_list.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*

/**
 * Created by Krishna Chaitanya Kandula on 9/18/17.
 */
class PokemonListViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private val LOG_TAG: String = PokemonListViewHolder::class.simpleName!!
        private val INITIAL_DATA_PATH: String = "initial_data.json"
    }

    fun onBind(pokemonListResult: PokemonListResult) {
        itemView.pokemonIdTextView_PokemonListItemView.text = "#${pokemonListResult.id}"
        itemView.pokemonNameTextView_pokemonListItemView.text = pokemonListResult.name
        itemView.pokemonDescriptionTextView_pokemonListItemView.text = pokemonListResult.description
        itemView.pokemonType1TextView_pokemonListItemView.text = pokemonListResult.type1.toString().toLowerCase().capitalize()
        if (pokemonListResult.type2 != null) {
            itemView.pokemonType2TextView_pokemonListItemView.text = pokemonListResult.type2.toString().toLowerCase().capitalize()
            itemView.pokemonType2TextView_pokemonListItemView.visibility = View.VISIBLE
        } else {
            itemView.pokemonType2TextView_pokemonListItemView.text = ""
            itemView.pokemonType2TextView_pokemonListItemView.visibility = View.GONE
        }

//        itemView.cardview_PokemonListItemView.setCardBackgroundColor(Color.parseColor(pokemonListResult.type1.color))
        changeTextColors(context.getColor(R.color.md_white_1000))
        changeOutlineColors(context.getColor(R.color.md_white_1000))

        Picasso.with(context)
                .load(pokemonListResult.imageUrl)
                .resizeDimen(R.dimen.minPokemonImageViewDiameter, R.dimen.minPokemonImageViewDiameter)
                .centerInside()
                .into(itemView.pokemonImageView_pokemonListItemView, object : Callback {
                    override fun onSuccess() {
                        generateColorPalette((((itemView.pokemonImageView_pokemonListItemView as ImageView).drawable) as BitmapDrawable).bitmap)
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : SingleObserver<Palette> {
                                    override fun onSuccess(palette: Palette?) {
                                        if (palette != null) {
                                            val defaultColor: Int = context.getColor(R.color.primary)
                                            itemView.cardview_PokemonListItemView.setCardBackgroundColor(palette.getLightMutedColor(defaultColor))
                                            storeColorPalette(pokemonListResult.id, palette)
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

    fun storeColorPalette(id: Int, palette: Palette) {
        try {
            val jsonArray: JSONArray? = parseInitialData(context)
            if (jsonArray != null) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(id)
                jsonObject.put("_color", palette.getLightMutedColor(context.getColor(R.color.primary)))
//                if(id==150) {
                    val file: File = File("${context.filesDir.absolutePath}colors_data.json")
                    Log.d(LOG_TAG, context.filesDir.absolutePath)
                    val output: Writer = BufferedWriter(FileWriter(file))
                    output.write(jsonArray.toString())
                    output.close()
//                }
            }
        } catch (e: JSONException) {
        }
    }

    private fun parseInitialData(context: Context): JSONArray? {
        val inputStream: InputStream = context.assets.open(INITIAL_DATA_PATH)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val jsonData = String(buffer)
        return JSONArray(jsonData)
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

    private fun generateColorPalette(bitmap: Bitmap): Single<Palette> {
        return Single.create { emitter ->
            val palette = Palette.from(bitmap).generate()
            emitter.onSuccess(palette)
        }
    }
}
