package com.canvas.krish.pokemanager.ui.pokemonlist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.canvas.krish.pokemanager.R
import com.canvas.krish.pokemanager.app.PokeManagerApplication
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import javax.inject.Inject

class PokemonListActivity : AppCompatActivity(), PokemonListContract.View {

    companion object {
        private val LOG_TAG: String = PokemonListActivity::class.simpleName!!
        private val DRAWER_ITEM_POKEDEX_IDENTIFIER: Long = 0
    }

    @Inject lateinit var presenter: PokemonListContract.Presenter

    private lateinit var navDrawer: Drawer
    private lateinit var listAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        DaggerPokemonListComponent.builder()
                .pokeManagerAppComponent((application as PokeManagerApplication).component)
                .pokemonListModule(PokemonListModule(this))
                .build()
                .inject(this)

        setupToolbar()
        setupNavDrawer()
        setupRecyclerView()

        swipeRefreshLayout_pokemonListActivity.setOnRefreshListener { presenter.onRefresh() }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_pokemonListActivity)
        supportActionBar?.title = getString(R.string.drawer_item_pokedex)
    }

    private fun setupNavDrawer() {
        var pokedexDrawerItem: PrimaryDrawerItem = PrimaryDrawerItem()
                .withName(getString(R.string.drawer_item_pokedex))
                .withIdentifier(DRAWER_ITEM_POKEDEX_IDENTIFIER)

        navDrawer = DrawerBuilder()
                .withActivity(this)
                .withTranslucentNavigationBar(false)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(pokedexDrawerItem)
                .build()
    }

    private fun setupRecyclerView() {
        listAdapter = PokemonListAdapter(this)
        pokemonRecyclerView_pokemonListActivity.adapter = listAdapter
        pokemonRecyclerView_pokemonListActivity.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
    }

    override fun showLoading() {
        swipeRefreshLayout_pokemonListActivity.isRefreshing = true
    }

    override fun stopLoading() {
        swipeRefreshLayout_pokemonListActivity.isRefreshing = false
    }

    override fun setData(pokemonListResults: List<PokemonListResult>) {
        listAdapter.data = pokemonListResults.toMutableList()
    }

    override fun updateData(additionalPokemonListResults: List<PokemonListResult>) {
    }

    override fun getExistingData(): List<PokemonListResult>? = null

    override fun showErrorLoadingData() {
        val error = "Unable to load data"
        Snackbar.make(window.decorView.rootView, error, Snackbar.LENGTH_SHORT).show()
    }
}
