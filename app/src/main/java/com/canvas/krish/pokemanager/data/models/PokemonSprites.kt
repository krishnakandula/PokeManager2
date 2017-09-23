package com.canvas.krish.pokemanager.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Krishna Chaitanya Kandula on 9/18/2017.
 */
@Parcelize
data class PokemonSprites(@SerializedName("back_female") val backFemale: String,
                          @SerializedName("back_shiny_female") val backShinyFemale: String,
                          @SerializedName("front_default") val frontDefault: String) : Parcelable