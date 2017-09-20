package com.canvas.krish.pokemanager.ui.pokemonlist

import android.util.Log
import com.canvas.krish.pokemanager.data.models.PokemonListResult
import com.canvas.krish.pokemanager.data.source.PokemonRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Krishna Chaitanya Kandula on 9/17/2017.
 */
class PokemonListPresenter @Inject constructor(private val pokemonRepository: PokemonRepository, private val view: PokemonListContract.View) : PokemonListContract.Presenter {

    companion object {
        private val RETRIEVAL_LIMIT: Int = 151
        private val LOG_TAG: String = PokemonListPresenter::class.simpleName!!
    }

    override fun start() {
        var existingData: List<PokemonListResult>? = view.getExistingData()
        if(existingData == null || existingData.isEmpty()) {
            getData(0, RETRIEVAL_LIMIT, false)
        }
    }

    override fun getData(offset: Int, limit: Int, refresh: Boolean) {
        view.showLoading()
        pokemonRepository.getPokemonList(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SingleObserver<List<PokemonListResult>> {
                    override fun onSubscribe(d: Disposable?) {
                        //Not implemented
                    }

                    override fun onSuccess(result: List<PokemonListResult>?) {
                        view.stopLoading()
                        if(result != null) {
                            view.setData(result)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        view.stopLoading()
                        Log.e(LOG_TAG, e!!.message, e)
                        view.showErrorLoadingData()
                    }
                })
    }

    override fun onRefresh() {
        getData(0, RETRIEVAL_LIMIT, false)
    }

    override fun onScroll(firstItemVisiblePosition: Int) {
        val color: String? = view.getExistingData()?.get(firstItemVisiblePosition)?.type1!!.color
        if(color != null) view.changeToolbarColor(color)
    }
}