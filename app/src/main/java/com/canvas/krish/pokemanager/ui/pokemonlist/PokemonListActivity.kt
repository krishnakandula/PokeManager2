package com.canvas.krish.pokemanager.ui.pokemonlist

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
        private val BUNDLE_RECYCLER_LAYOUT: String = "pokemonlistactivity.recycler.layout"
        private val BUNDLE_RECYCLER_DATA: String = "pokemonlistactivitiy.recycler.data"
    }

    @Inject lateinit var presenter: PokemonListContract.Presenter

    private lateinit var navDrawer: Drawer
    private lateinit var listAdapter: PokemonListAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var listState: Parcelable? = null

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
        if(listState != null) {
            layoutManager.onRestoreInstanceState(listState)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_pokemonListActivity)
        supportActionBar?.title = getString(R.string.drawer_item_pokedex)
        toolbar_pokemonListActivity.setTitleTextColor(getColor(R.color.md_white_1000))
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
        layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)

        pokemonRecyclerView_pokemonListActivity.adapter = listAdapter
        pokemonRecyclerView_pokemonListActivity.layoutManager = layoutManager
        pokemonRecyclerView_pokemonListActivity.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                presenter.onScroll(layoutManager.findFirstVisibleItemPosition())
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        //Save recyclerview state
        outState?.putParcelable(BUNDLE_RECYCLER_LAYOUT, pokemonRecyclerView_pokemonListActivity.layoutManager.onSaveInstanceState())

        //Save data
        val savedData: ArrayList<PokemonListResult> = ArrayList()
        savedData.addAll(listAdapter.data)
        outState?.putParcelableArrayList(BUNDLE_RECYCLER_DATA, savedData)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            //Restore data
            val restoredData: ArrayList<PokemonListResult> = savedInstanceState.getParcelableArrayList(BUNDLE_RECYCLER_DATA)
            listAdapter.data = restoredData.toMutableList()

            //Restore recyclerview state
            listState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT)
            layoutManager.onRestoreInstanceState(listState)
        }
    }

    override fun onPause() {
        super.onPause()
        listState = layoutManager.onSaveInstanceState()
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

    override fun getExistingData(): List<PokemonListResult>? = listAdapter.data

    override fun showErrorLoadingData() {
        val error = "Unable to load data"
        Snackbar.make(window.decorView.rootView, error, Snackbar.LENGTH_LONG).show()
    }

    override fun changeToolbarColor(color: String) {
        val parsedColor: Int = Color.parseColor(color)
        toolbar_pokemonListActivity.setBackgroundColor(parsedColor)
        window.statusBarColor = parsedColor
    }
}
