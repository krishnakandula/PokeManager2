package com.canvas.krish.pokemanager.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Krishna Chaitanya Kandula on 9/16/2017.
 */
@Parcelize
data class PokemonListResult(val id: Int,
                             val name: String,
                             val spriteUri: String,
                             val description: String,
                             val imageUrl: String) : Parcelable