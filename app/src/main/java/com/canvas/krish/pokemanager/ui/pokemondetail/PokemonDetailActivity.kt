package com.canvas.krish.pokemanager.ui.pokemondetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.data.models.Pokemon

class PokemonDetailActivity : AppCompatActivity(), PokemonDetailContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setData(data: Pokemon) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getExistingData(): Pokemon? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorLoadingData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
