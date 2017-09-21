package com.canvas.krish.pokemanager.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Krishna Chaitanya Kandula on 9/21/2017.
 */
@Parcelize
data class PokemonColorPalette(val lightMuted: Int,
                               val darkMuted: Int,
                               val dominant: Int,
                               val darkVibrant: Int,
                               val lightVibrant: Int,
                               val muted: Int,
                               val vibrant: Int) : Parcelable