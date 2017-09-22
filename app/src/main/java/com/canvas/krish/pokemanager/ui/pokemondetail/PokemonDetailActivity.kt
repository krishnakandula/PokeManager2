package com.canvas.krish.pokemanager.ui.pokemondetail

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.app.PokeManagerApplication
import com.canvas.krish.pokemanager.data.models.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import javax.inject.Inject

class PokemonDetailActivity : AppCompatActivity(), PokemonDetailContract.View {

    companion object {
        private val activity: String = PokemonDetailActivity::class.simpleName!!
        val bundle_id_key: String = "$activity.Bundle.Id.Key"
        val bundle_pokemon_data: String = "$activity.Bundle.Pokemon.Data"
    }

    @Inject lateinit var presenter: PokemonDetailContract.Presenter

    private var data: Pokemon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        val pokemonId: Int = intent.getIntExtra(bundle_id_key, 1)

        DaggerPokemonDetailComponent.builder()
                .pokeManagerAppComponent((application as PokeManagerApplication).component)
                .pokemonDetailModule(PokemonDetailModule(this, pokemonId))
                .build()
                .inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        if(this.data != null) {
            outState?.putParcelable(bundle_pokemon_data, this.data)
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState != null) {
            val restoredData: Pokemon = savedInstanceState.getParcelable(bundle_pokemon_data)
            this.data = restoredData
        }
    }

    override fun showLoading() {
        //Not implemented
    }

    override fun stopLoading() {
        //Not implemented
    }

    override fun setData(data: Pokemon) {
        this.data = data
    }

    override fun getExistingData(): Pokemon? {
        return this.data
    }

    override fun showErrorLoadingData() {
        Snackbar.make(layout_PokemonListActivity, "Unable to retrieve data", Snackbar.LENGTH_LONG).show()
    }
}
