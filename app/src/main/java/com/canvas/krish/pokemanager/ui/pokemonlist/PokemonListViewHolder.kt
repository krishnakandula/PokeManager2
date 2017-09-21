package com.canvas.krish.pokemanager.ui.pokemonlist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.squareup.picasso.Picasso
import io.reactivex.Single
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
        private var dataArray: JSONArray? = null
        private var numberOfWrittenColors: Int = 0
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

        if(pokemonListResult.palette != null) {
            itemView.cardview_PokemonListItemView.setCardBackgroundColor(pokemonListResult.palette.lightMuted)
        }
        changeTextColors(context.getColor(R.color.md_white_1000))
        changeOutlineColors(context.getColor(R.color.md_white_1000))

        Picasso.with(context)
                .load(pokemonListResult.imageUrl)
                .resizeDimen(R.dimen.minPokemonImageViewDiameter, R.dimen.minPokemonImageViewDiameter)
                .centerInside()
                .into(itemView.pokemonImageView_pokemonListItemView)
    }

    fun storeColorPalette(id: Int, palette: Palette) {
        try {
            val jsonArray: JSONArray? = parseInitialData(context)
            if (jsonArray != null) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(id - 1)
                if (!jsonObject.has("-color")) {

                    val colorJsonObj: JSONObject = JSONObject()
                    colorJsonObj.put("_lightMuted", palette.getLightMutedColor(context.getColor(R.color.primary)))
                    colorJsonObj.put("_darkMuted", palette.getDarkMutedColor(context.getColor(R.color.primary)))
                    colorJsonObj.put("_dominant", palette.getDominantColor(context.getColor(R.color.primary)))
                    colorJsonObj.put("_darkVibrant", palette.getDarkVibrantColor(context.getColor(R.color.primary)))
                    colorJsonObj.put("_lightVibrant", palette.getLightVibrantColor(context.getColor(R.color.primary)))
                    colorJsonObj.put("_muted", palette.getMutedColor(context.getColor(R.color.primary)))
                    colorJsonObj.put("_vibrant", palette.getVibrantColor(context.getColor(R.color.primary)))
                    jsonObject.put("_color", colorJsonObj)
                    dataArray = jsonArray

                    if(id == 151) {
                        Log.d(LOG_TAG, "f")
                    }
                    numberOfWrittenColors++
                    Log.d(LOG_TAG, "$numberOfWrittenColors")
                    if (numberOfWrittenColors == 151) {
                        val file: File = File("${context.filesDir.absolutePath}colors_data.json")
                        Log.d(LOG_TAG, context.filesDir.absolutePath)
                        val output: Writer = BufferedWriter(FileWriter(file))
                        output.write(jsonArray.toString())
                        output.close()
                    }
                }
            }
        } catch (e: JSONException) {
        }
    }

    private fun parseInitialData(context: Context): JSONArray? {
        if (dataArray == null) {
            val inputStream: InputStream = context.assets.open(INITIAL_DATA_PATH)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val jsonData = String(buffer)
            dataArray = JSONArray(jsonData)
        }
        return dataArray
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
