package com.canvas.krish.pokemanager.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Krishna Chaitanya Kandula on 9/18/2017.
 */
@Parcelize
data class Pokemon(val id: Int,
                   val name: String,
                   val sprites: PokemonSprites) : Parcelable
